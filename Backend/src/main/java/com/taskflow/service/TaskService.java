package com.taskflow.service;

import com.taskflow.model.Task;
import com.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task save(Task task) {
        return taskRepository.save(task);
    }


    public List<Task> findByUser(Long userId) {
        return taskRepository.findByUserUserId(userId);
    }


    public Task update(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(task);
    }


    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}