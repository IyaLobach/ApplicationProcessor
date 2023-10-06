package com.example.ApplicationsProcessor.util;

import java.time.LocalDateTime;

public class ApplicationErrorResponse {

  private String message;

  public ApplicationErrorResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
