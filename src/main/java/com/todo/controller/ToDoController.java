package com.todo.controller;

import com.todo.dto.ResponseDTO;
import com.todo.dto.ToDoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todo")
public class ToDoController {

    @PostMapping("select/todo")
    public String select(@RequestBody ToDoDto dto){
        //vo에 받기
        //받은 vo가 null 값인지 체크

        return dto.getMessage();
    }
}
