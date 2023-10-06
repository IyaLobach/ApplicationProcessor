package com.example.ApplicationsProcessor.dto;

import com.example.ApplicationsProcessor.models.Status;
import java.util.Date;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class ApplicationDTO {

  @NotEmpty(message = "Текст заявки пуст")
  private String text;

  private Status status;

  public ApplicationDTO() {
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
