package com.example.ApplicationsProcessor.dto;

import com.example.ApplicationsProcessor.models.Status;
import java.util.Date;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class ApplicationDTO {

  @NotEmpty
  private String text;

  private Status status;

  private Date date;

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
