package com.task.services;

import com.task.dto.TaskDTO;
import com.task.entities.Task;
import com.task.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskDTO addTask(TaskDTO taskDTO) {
        var task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        Task persisted = taskRepository.save(task);
        BeanUtils.copyProperties(persisted, taskDTO);
        return taskDTO;
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void updateTask(Integer id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setDone(true);
            taskRepository.save(task);
        } else {
            System.out.println("Tarefa não enccontrada!");
        }
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

}
