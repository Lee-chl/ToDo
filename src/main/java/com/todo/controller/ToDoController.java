package com.todo.controller;

import com.todo.dto.ResponseDTO;
import com.todo.dto.ToDoDto;
import com.todo.service.TodoService;
import com.todo.vo.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TodoService service;

    @PostMapping("select/todo")
    public ResponseEntity<List<TodoVo>> select(@RequestBody ToDoDto dto){
        List<TodoVo> resultData = service.getTodoList(dto);
        if(resultData == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultData);
    }
}
