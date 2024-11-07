package com.kenny.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kenny.todosimple.models.Task;
import com.kenny.todosimple.models.User;
import com.kenny.todosimple.repositories.TaskRepository;
import com.kenny.todosimple.repositories.UserRepository;

public class TaskService {

  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  private UserService userService;

  public Task findById(Long id) {
    Optional<Task> task = this.taskRepository.findById(id);
    return task
        .orElseThrow(() -> new RuntimeException("Tarefa nao encontrado! " + id + ", Tipo:" + User.class.getName()));
  }

  @Transactional
  public Task create(Task obj) {
    User user = this.userService.findById(obj.getUser().getId());
    obj.setId(null);
    obj.setUser(user);
    obj = this.taskRepository.save(obj);
    return obj;
  }

  @Transactional
  public Task update(Task obj) {
    Task newObj = findById(obj.getId());
    newObj.setDescription(obj.getDescription());
    return this.taskRepository.save(newObj);
  }

  public void delete(Long idd) {
    findById(idd);
    this.taskRepository.deleteById(idd);
  }

}
