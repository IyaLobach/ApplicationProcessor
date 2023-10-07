package com.example.ApplicationsProcessor.dto;

import com.example.ApplicationsProcessor.models.Role;
import java.util.List;
import javax.validation.constraints.Email;

public class UserDTO {
  private String name;
  private String surname;
  @Email
  private String email;
  private List<Role> role;

  public UserDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Role> getRole() {
    return role;
  }

  public void setRole(List<Role> role) {
    this.role = role;
  }
}
