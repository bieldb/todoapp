package com.bieldb.todoapp.mapper;

import org.springframework.stereotype.Component;

import com.bieldb.todoapp.dto.TaskRequestDTO;
import com.bieldb.todoapp.dto.TaskResponseDTO;
import com.bieldb.todoapp.model.Task;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitulo(dto.getTitulo());
        task.setDescricao(dto.getDescricao());
        task.setStatus(dto.getStatus());
        task.setDataLimite(dto.getDataLimite());
        return task;
    }

    public TaskResponseDTO toDTO(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitulo(task.getTitulo());
        taskResponseDTO.setDescricao(task.getDescricao());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setDataLimite(task.getDataLimite());
        return taskResponseDTO;
    }
}
