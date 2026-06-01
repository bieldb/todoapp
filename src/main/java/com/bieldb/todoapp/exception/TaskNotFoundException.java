package com.bieldb.todoapp.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task com id " + id + " não foi encontrada.");
    }
}
