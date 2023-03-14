package com.task.utils;

import com.task.dto.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel {

    private String message;
    private TaskDTO taskDTO;

}
