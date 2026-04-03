package com.taskflow.controller;

import com.taskflow.model.Task;
import com.taskflow.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.save(task);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getByUser(@PathVariable Long userId) {
        return taskService.findByUser(userId);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}