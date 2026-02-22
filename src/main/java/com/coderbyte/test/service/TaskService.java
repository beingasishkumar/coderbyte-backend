package com.coderbyte.test.service;

import com.coderbyte.test.entity.Task;
import com.coderbyte.test.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task get(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public Task update(@Valid Task task) {
        get(task.getId());
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.delete(Task.builder().id(id).build());
    }

    public Task save(@Valid Task task) {
        return taskRepository.save(task);
    }
}
