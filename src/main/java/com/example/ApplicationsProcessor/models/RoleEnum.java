package com.example.ApplicationsProcessor.models;

public enum RoleEnum {
  USER ("Пользователь"),
  ADMIN ("Администратор"),
  OPERATOR ("Оператор");

  private String title;

  RoleEnum(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}