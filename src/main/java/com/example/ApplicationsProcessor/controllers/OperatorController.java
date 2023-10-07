package com.example.ApplicationsProcessor.controllers;

import com.example.ApplicationsProcessor.dto.ApplicationDTO;
import com.example.ApplicationsProcessor.models.Application;
import com.example.ApplicationsProcessor.models.Status;
import com.example.ApplicationsProcessor.services.ApplicationService;
import com.example.ApplicationsProcessor.util.ErrorResponse;
import com.example.ApplicationsProcessor.util.ApplicationException;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operator")
public class OperatorController {

  @Autowired
  private ApplicationService applicationService;

  @Autowired
  private ModelMapper modelMapper;

  @PatchMapping("/{operatorId}/applications/{applicationId}/accept")
  public ResponseEntity<HttpStatus> accept(
      @PathVariable("applicationId") int applicationId) {
    applicationService.accept(applicationId);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PatchMapping("/{operatorId}/applications/{applicationId}/reject")
  public ResponseEntity<HttpStatus> reject (
      @PathVariable("applicationId") int applicationId) {
    applicationService.reject(applicationId);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping("/{operatorId}/applications/")
  public ResponseEntity<List<ApplicationDTO>> show() {
    List<Application> applicationList = applicationService.showApplicationByStatus(Status.SUBMITTED);
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
