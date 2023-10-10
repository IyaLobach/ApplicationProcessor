package com.example.ApplicationsProcessor.controllers;

import com.example.ApplicationsProcessor.security.UserDetail;
import com.example.ApplicationsProcessor.util.ErrorResponse;
import com.example.ApplicationsProcessor.util.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

  @GetMapping("/login")
  public ResponseEntity<HttpStatus> login() {
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping("/logout")
  public  ResponseEntity<HttpStatus> logout() {
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handlerException(
      UserException userException) {
    ErrorResponse errorResponse = new ErrorResponse(
        userException.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}