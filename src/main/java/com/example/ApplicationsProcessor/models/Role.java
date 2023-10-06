package com.example.ApplicationsProcessor.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(schema = "application_processor", name = "role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private int id;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private RoleEnum role;

  @ManyToMany(mappedBy = "role")
  private List<User> users;

  public Role() {
  }

  public Role(RoleEnum role) {
    this.role = role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public void addUser(User user) {
    users.add(user);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
