package com.kenny.todosimple.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

  public interface CreateUser {
  }

  public interface UpdateUser {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  private Long id;

  @Column(name = "username", length = 100, nullable = false, unique = true)
  @NotNull(groups = CreateUser.class)
  @NotEmpty(groups = CreateUser.class)
  @Size(groups = CreateUser.class, min = 4, max = 100)
  private String username;

  @JsonProperty(access = Access.WRITE_ONLY)
  @Column(name = "password", length = 60, nullable = false)
  @NotNull(groups = { CreateUser.class, UpdateUser.class })
  @NotEmpty(groups = { CreateUser.class, UpdateUser.class })
  @Size(groups = { CreateUser.class, UpdateUser.class }, min = 8, max = 60)
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Task> tasks = new ArrayList<Task>();

  public User() {
  }

  public User(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public List<Task> getTasks() {
    return this.tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }


}
