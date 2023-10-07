package com.example.ApplicationsProcessor.controllers;

import com.example.ApplicationsProcessor.dto.ApplicationDTO;
import com.example.ApplicationsProcessor.models.Application;
import com.example.ApplicationsProcessor.services.ApplicationService;
import com.example.ApplicationsProcessor.services.UserService;
import com.example.ApplicationsProcessor.util.ErrorResponse;
import com.example.ApplicationsProcessor.util.ApplicationException;
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


  /** создание новой заявки */
  @PostMapping("/{userId}/applications")
  public ResponseEntity<HttpStatus> create (
      @PathVariable("userId") int userId,
      @RequestBody @Valid ApplicationDTO applicationDTO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      List<FieldError> errorList = bindingResult.getFieldErrors();
      StringBuilder message = new StringBuilder();
      for (FieldError error : errorList) {
        message.append(error.getDefaultMessage());
      }
      throw new ApplicationException(message.toString());
    }
    applicationService
        .create(modelMapper.map(applicationDTO, Application.class), userService.findById(userId));

    return ResponseEntity.ok(HttpStatus.OK);
  }

  /** отправка заявки = обновление статуса заявки */
  @PatchMapping("/{userId}/applications/{applicationId}/submit")
  public ResponseEntity<HttpStatus> submit (@PathVariable("applicationId") int applicationId) {
    applicationService.submit(applicationId);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  /** редактирование заявки = обновление текста заявки */
  @PatchMapping("/{userId}/applications/{applicationId}/edit")
  public ResponseEntity<HttpStatus> update (
      @PathVariable("applicationId") int applicationId,
      @RequestBody @Valid ApplicationDTO applicationDTO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      List<FieldError> errorList = bindingResult.getFieldErrors();
      StringBuilder message = new StringBuilder();
      for (FieldError error : errorList) {
        message.append(error.getDefaultMessage());
      }
      throw new ApplicationException(message.toString());
    }
    applicationService.updateText(applicationId, modelMapper.map(applicationDTO, Application.class).getText());

    return ResponseEntity.ok(HttpStatus.OK);
  }

  // проблема N + 1 и ПАГИНАЦИЯ
  // ВЫВОД ПО УСЛОВИЮ ЗАДАНИЯ!!!
  /** просмотр заявок ПОКА БЕЗ ПАГИНАЦИИ  */
  @GetMapping("/{userId}/applications")
  public ResponseEntity<List<ApplicationDTO>> show(@PathVariable("userId") int userId) {
    List<Application> applicationList = applicationService.showApplicationByUserId(userId);
    ArrayList<ApplicationDTO> applicationDTOList = new ArrayList<>();
    for (Application application : applicationList)
      applicationDTOList.add(modelMapper.map(application, ApplicationDTO.class));
    return new ResponseEntity<>(applicationDTOList, HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handlerException(
      ApplicationException applicationException) {
    ErrorResponse applicationErrorResponse = new ErrorResponse(
        applicationException.getMessage());

    return new ResponseEntity<>(applicationErrorResponse, HttpStatus.BAD_REQUEST);
  }
}
