package com.kenny.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenny.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByUser_Id(Long id);

  // @Query(value = "SELECT t From Task t WHERE t.user.id = :id")
  // List<Task> findByUserId(@Param("id") Long id);

  // @Query(value = "SELECT * From task t WHERE t.user_id = :id",nativeQuery =
  // true)
  // List<Task> findByUserId(@Param("id") Long id);
}
