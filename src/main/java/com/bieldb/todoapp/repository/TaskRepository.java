package com.bieldb.todoapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bieldb.todoapp.model.Task;
import com.bieldb.todoapp.model.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByDataLimiteBefore(LocalDate hoje);

}
