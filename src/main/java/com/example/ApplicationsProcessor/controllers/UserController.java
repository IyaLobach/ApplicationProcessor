package com.example.ApplicationsProcessor.controllers;

import com.example.ApplicationsProcessor.dto.ApplicationDTO;
import com.example.ApplicationsProcessor.models.Application;
import com.example.ApplicationsProcessor.services.ApplicationService;
import com.example.ApplicationsProcessor.services.UserService;
import com.example.ApplicationsProcessor.util.ApplicationErrorResponse;
import com.example.ApplicationsProcessor.util.ApplicationNotCreatedException;
import com.example.ApplicationsProcessor.util.ApplicationNotSubmittedException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ApplicationService applicationService;

  @Autowired
  private ModelMapper modelMapper;

  // НАДО ВОЗВРАЩАТЬ JSON!!!!

  /** создание новой заявки */
  @PostMapping("/{userId}/applications")
  public ResponseEntity<HttpStatus> createNewApplication(
      @PathVariable("userId") int userId,
      @RequestBody @Valid ApplicationDTO applicationDTO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      List<FieldError> errorList = bindingResult.getFieldErrors();
      StringBuilder message = new StringBuilder();
      for (FieldError error : errorList) {
        message.append(error.getDefaultMessage());
      }
      throw new ApplicationNotCreatedException(message.toString());
    }
    applicationService
        .create(modelMapper.map(applicationDTO, Application.class), userService.findById(userId));

    return ResponseEntity.ok(HttpStatus.OK);
  }

  /** отправка заявки = обновление статуса заявки */
  @PatchMapping("/{userId}/applications/{applicationId}")
  public ResponseEntity<HttpStatus> updateStatus(@PathVariable("applicationId") int applicationId) {
    applicationService.updateStatus(applicationId);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  /** редактирование заявки = обновление текста заявки */
  @PatchMapping("/{userId}/applications/{applicationId}")
  public ResponseEntity<HttpStatus> updateText(
      @PathVariable("applicationId") int applicationId,
      @RequestBody @Valid ApplicationDTO applicationDTO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      List<FieldError> errorList = bindingResult.getFieldErrors();
      StringBuilder message = new StringBuilder();
      for (FieldError error : errorList) {
        message.append(error.getDefaultMessage());
      }
      throw new ApplicationNotCreatedException(message.toString());
    }
    applicationService.updateText(applicationId, modelMapper.map(applicationDTO, Application.class).getText());

    return ResponseEntity.ok(HttpStatus.OK);
  }

  // ЧТО ТУТ ВОЗВРАЩАТЬ??? По идее Response
  // есть реализация в проекте №3 в 15 модуле
  // проблема N + 1!!!!!
  /** просмотр заявок ПОКА БЕЗ ПАГИНАЦИИ  */
  @GetMapping("/{userId}/applications")
  public ResponseEntity<List<ApplicationDTO>> getApplication(@PathVariable("userId") int userId) {
    List<Application> applicationList = applicationService.showApplication(userId);
    ArrayList<ApplicationDTO> applicationDTOList = new ArrayList<>();
    for (Application application : applicationList)
      applicationDTOList.add(modelMapper.map(application, ApplicationDTO.class));
    return new ResponseEntity<>(applicationDTOList, HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<ApplicationErrorResponse> handlerException(
      ApplicationNotCreatedException applicationNotCreatedException) {
    ApplicationErrorResponse applicationErrorResponse = new ApplicationErrorResponse(
        "Заявка не была добавлена");

    return new ResponseEntity<>(applicationErrorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  private ResponseEntity<ApplicationErrorResponse> handlerException(
      ApplicationNotSubmittedException applicationNotSubmittedException) {
    ApplicationErrorResponse applicationErrorResponse = new ApplicationErrorResponse(
        "Заявка не была отправлена");

    return new ResponseEntity<>(applicationErrorResponse, HttpStatus.NOT_FOUND);
  }
}
