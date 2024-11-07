package com.kenny.todosimple.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kenny.todosimple.models.User;
import com.kenny.todosimple.models.User.CreateUser;
import com.kenny.todosimple.models.User.UpdateUser;
import com.kenny.todosimple.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

  @Autowired
  private UserService userService;

  public ResponseEntity<User> findById(@PathVariable Long id) {
    User obj = userService.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  @Validated(CreateUser.class)
  public ResponseEntity<Void> create(@Valid @RequestBody User obj) {
    this.userService.create(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping
  @Validated(UpdateUser.class)
  public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) {
    obj.setId(id);
    this.userService.update(obj);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.userService.delete(id);
    return ResponseEntity.noContent().build(); 
  }
}
