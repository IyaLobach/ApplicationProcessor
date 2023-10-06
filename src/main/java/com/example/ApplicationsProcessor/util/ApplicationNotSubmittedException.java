package com.example.ApplicationsProcessor.util;

public class ApplicationNotSubmittedException extends RuntimeException{

  public ApplicationNotSubmittedException(String message) {
    super(message);
  }
}
