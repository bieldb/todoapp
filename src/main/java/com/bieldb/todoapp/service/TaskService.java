package com.bieldb.todoapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bieldb.todoapp.dto.TaskRequestDTO;
import com.bieldb.todoapp.dto.TaskResponseDTO;
import com.bieldb.todoapp.exception.TaskNotFoundException;
import com.bieldb.todoapp.mapper.TaskMapper;
import com.bieldb.todoapp.model.Task;
import com.bieldb.todoapp.model.TaskStatus;
import com.bieldb.todoapp.repository.TaskRepository;

@Service
public class TaskService {
    TaskRepository taskRepository;
    TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }
    
    public TaskResponseDTO post(TaskRequestDTO dto) {
        Task task = taskMapper.toEntity(dto);
        task = taskRepository.save(task);
        return taskMapper.toDTO(task);
    }

    public List<TaskResponseDTO> getAll() {
        Sort sort = Sort.by("status").descending()
        .and(Sort.by("titulo").ascending());
        List<Task> tasks = taskRepository.findAll(sort);
        List<TaskResponseDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(taskMapper.toDTO(task));
        }
        return dtos;
    }

    public TaskResponseDTO getById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toDTO(task);
    }

    public List<TaskResponseDTO> getByStatus(TaskStatus status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        List<TaskResponseDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(taskMapper.toDTO(task));
        }
        return dtos;
    }

    public List<TaskResponseDTO> getVencidas() {
        LocalDate hoje = LocalDate.now();
        List<Task> tasks = taskRepository.findByDataLimiteBefore(hoje);
        List<TaskResponseDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(taskMapper.toDTO(task));
        }
        return dtos;
    }
}
