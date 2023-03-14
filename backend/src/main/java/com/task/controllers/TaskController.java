package com.task.controllers;

import com.task.dto.TaskDTO;
import com.task.entities.Task;
import com.task.services.TaskService;
import com.task.utils.Messages;
import com.task.utils.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ResponseModel> addTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO persisted = taskService.addTask(taskDTO);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage(Messages.MESSAGE_1.getDescription());
        responseModel.setTaskDTO(persisted);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    @GetMapping("/tasks")
    public ResponseEntity <List<Task>> getAllTask() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTask());
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Integer id) {
        taskService.updateTask(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}