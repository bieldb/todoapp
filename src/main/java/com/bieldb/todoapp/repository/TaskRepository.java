package com.bieldb.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bieldb.todoapp.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
