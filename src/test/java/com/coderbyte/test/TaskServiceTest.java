package com.coderbyte.test;

import com.coderbyte.test.entity.Task;
import com.coderbyte.test.repository.TaskRepository;
import com.coderbyte.test.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task sampleTask;

    @BeforeEach
    void setUp() {
        sampleTask = new Task();
        sampleTask.setId(1L);
        sampleTask.setTitle("Finish Assessment");
        sampleTask.setIsCompleted(false);
    }

    @Test
    void shouldCreateTaskSuccessfully() {
        when(taskRepository.save(any(Task.class))).thenReturn(sampleTask);

        Task savedTask = taskService.save(sampleTask);

        assertNotNull(savedTask);
        assertEquals("Finish Assessment", savedTask.getTitle());
        verify(taskRepository, times(1)).save(sampleTask);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskService.get(1L);
        });

        assertEquals("Task not found with id: 1", exception.getMessage());
    }

    @Test
    void shouldUpdateTaskStatus() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask));
        when(taskRepository.save(any(Task.class))).thenReturn(sampleTask);

        sampleTask.setIsCompleted(true);
        Task updatedTask = taskService.update(sampleTask);

        assertTrue(updatedTask.getIsCompleted());
        verify(taskRepository).save(sampleTask);
    }
}
