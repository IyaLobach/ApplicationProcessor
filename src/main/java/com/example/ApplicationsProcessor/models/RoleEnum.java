package com.example.ApplicationsProcessor.models;

public enum RoleEnum {
  ROLE_USER ("Пользователь"),
  ROLE_ADMIN ("Администратор"),
  ROLE_OPERATOR ("Оператор");

  private String title;

  RoleEnum(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}