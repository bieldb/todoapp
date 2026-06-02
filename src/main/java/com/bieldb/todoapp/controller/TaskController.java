package com.bieldb.todoapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bieldb.todoapp.dto.TaskRequestDTO;
import com.bieldb.todoapp.dto.TaskResponseDTO;
import com.bieldb.todoapp.model.TaskStatus;
import com.bieldb.todoapp.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskService taskService;

    

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponseDTO post(@Valid @RequestBody TaskRequestDTO dto) {
        return taskService.post(dto);
    }

    @GetMapping
    public List<TaskResponseDTO> findAll() {
        return taskService.getAll();
    }

    @GetMapping("{id}")
    public TaskResponseDTO findById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping("/status/{status}")
    public List<TaskResponseDTO> findByStatus(@PathVariable TaskStatus status) {
        return taskService.getByStatus(status);
    }

    @GetMapping("/vencidas")
    public List<TaskResponseDTO> findVencidas() {
        return taskService.getVencidas();
    }

    @PutMapping("{id}")
    public TaskResponseDTO update(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO dto) {
        return taskService.update(id, dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
