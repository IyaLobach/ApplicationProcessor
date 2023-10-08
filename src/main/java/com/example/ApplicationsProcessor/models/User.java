package com.example.ApplicationsProcessor.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "application_processor", name = "user")
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "user_name")
  private String name;

  @Column(name = "user_surname")
  private String surname;

  @Column(name = "email")
  private String email;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  @ManyToMany
  @JoinTable(
      schema = "application_processor",
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name="role")
  )
  private List<Role> role;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Application> applications;

  public User() {
  }

  public int getId() {
    return id;
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

  public List<Application> getApplications() {
    return applications;
  }

  public void setApplications(
      List<Application> applications) {
    this.applications = applications;
  }

  public List<Role> getRole() {
    return role;
  }

  public void setRole(List<Role> role) {
    this.role = role;
  }

  public void addApplication(Application application) {
    if (applications == null)
      applications = new ArrayList<>();
    applications.add(application);
  }

  public void addRole(Role role) {
    if (this.role == null)
      this.role = new ArrayList<>();
    this.role.add(role);
  }

  public void setId(int id) {
    this.id = id;
  }
}
