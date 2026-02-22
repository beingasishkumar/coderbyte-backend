package com.coderbyte.test.controller;

import com.coderbyte.test.entity.Task;
import com.coderbyte.test.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> save(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.save(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.get(id));
    }

    @PutMapping
    public ResponseEntity<Task> update(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
