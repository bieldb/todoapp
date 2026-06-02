package com.bieldb.todoapp.service;

import com.bieldb.todoapp.dto.TaskRequestDTO;
import com.bieldb.todoapp.dto.TaskResponseDTO;
import com.bieldb.todoapp.exception.TaskNotFoundException;
import com.bieldb.todoapp.mapper.TaskMapper;
import com.bieldb.todoapp.model.Task;
import com.bieldb.todoapp.model.TaskStatus;
import com.bieldb.todoapp.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {


    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnTaskWhenIdExists() {

        // Arrange
        Task task = new Task();
        task.setId(1L);
        task.setTitulo("Estudar Spring");

        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(1L);
        dto.setTitulo("Estudar Spring");

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        when(taskMapper.toDTO(task))
                .thenReturn(dto);

        // Act
        TaskResponseDTO resultado = taskService.getById(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Estudar Spring", resultado.getTitulo());

        verify(taskRepository).findById(1L);
        verify(taskMapper).toDTO(task);
    }

    @Test
    void shouldThrowTaskNotFoundExceptionWhenIdDoesNotExist() {

        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getById(1L)
        );

        verify(taskRepository).findById(1L);
    }

    @Test
    void shouldCreateTask() {

        TaskRequestDTO request = new TaskRequestDTO();
        request.setTitulo("Nova Task");

        Task task = new Task();
        task.setTitulo("Nova Task");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitulo("Nova Task");

        TaskResponseDTO response = new TaskResponseDTO();
        response.setId(1L);
        response.setTitulo("Nova Task");

        when(taskMapper.toEntity(request))
                .thenReturn(task);

        when(taskRepository.save(task))
                .thenReturn(savedTask);

        when(taskMapper.toDTO(savedTask))
                .thenReturn(response);

        TaskResponseDTO resultado = taskService.post(request);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        verify(taskRepository).save(task);
    }

    @Test
    void shouldReturnAllTasks() {

        Task task1 = new Task();
        task1.setId(1L);

        Task task2 = new Task();
        task2.setId(2L);

        TaskResponseDTO dto1 = new TaskResponseDTO();
        dto1.setId(1L);

        TaskResponseDTO dto2 = new TaskResponseDTO();
        dto2.setId(2L);

        when(taskRepository.findAll(any(Sort.class)))
                .thenReturn(List.of(task1, task2));

        when(taskMapper.toDTO(task1))
                .thenReturn(dto1);

        when(taskMapper.toDTO(task2))
                .thenReturn(dto2);

        List<TaskResponseDTO> resultado = taskService.getAll();

        assertEquals(2, resultado.size());

        verify(taskRepository).findAll(any(Sort.class));
    }

    @Test
    void shouldReturnTasksByStatus() {

        Task task = new Task();
        task.setStatus(TaskStatus.PENDENTE);

        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setStatus(TaskStatus.PENDENTE);

        when(taskRepository.findByStatus(TaskStatus.PENDENTE))
                .thenReturn(List.of(task));

        when(taskMapper.toDTO(task))
                .thenReturn(dto);

        List<TaskResponseDTO> resultado =
                taskService.getByStatus(TaskStatus.PENDENTE);

        assertEquals(1, resultado.size());

        verify(taskRepository)
                .findByStatus(TaskStatus.PENDENTE);
    }

    @Test
    void shouldReturnOverdueTasks() {

        Task task = new Task();

        TaskResponseDTO dto = new TaskResponseDTO();

        when(taskRepository.findByDataLimiteBefore(any(LocalDate.class)))
                .thenReturn(List.of(task));

        when(taskMapper.toDTO(task))
                .thenReturn(dto);

        List<TaskResponseDTO> resultado =
                taskService.getVencidas();

        assertEquals(1, resultado.size());

        verify(taskRepository)
                .findByDataLimiteBefore(any(LocalDate.class));
    }

    @Test
    void shouldUpdateTask() {

        Task task = new Task();
        task.setId(1L);

        TaskRequestDTO request = new TaskRequestDTO();
        request.setTitulo("Atualizada");

        TaskResponseDTO response = new TaskResponseDTO();
        response.setId(1L);
        response.setTitulo("Atualizada");

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        when(taskRepository.save(task))
                .thenReturn(task);

        when(taskMapper.toDTO(task))
                .thenReturn(response);

        TaskResponseDTO resultado =
                taskService.update(1L, request);

        assertEquals("Atualizada",
                resultado.getTitulo());

        verify(taskRepository).save(task);
    }

    @Test
    void shouldThrowTaskNotFoundExceptionWhenUpdatingNonExistingTask() {

        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.update(
                        1L,
                        new TaskRequestDTO()
                )
        );
    }

    @Test
    void shouldDeleteTask() {

        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        taskService.deleteById(1L);

        verify(taskRepository).delete(task);
    }

    @Test
    void shouldThrowTaskNotFoundExceptionWhenDeletingNonExistingTask() {

        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.deleteById(1L)
        );
    }
}