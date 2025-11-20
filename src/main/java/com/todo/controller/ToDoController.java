package com.todo.controller;

import com.todo.dto.ResponseDTO;
import com.todo.dto.ToDoDto;
import com.todo.service.TodoService;
import com.todo.vo.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String select(@RequestBody ToDoDto dto){
        //DTO null 체크
        //vo에 받기
        //TodoVo resultData = service.testService();
        //받은 vo가 null 값인지 체크
        //그 후 서비스로 연결 (밑은 예시로 만든 것)
        //String str = service.testService();

        //ResponseDTO<String> response = ResponseDTO.<String>builder().data(str).build();

        //return ResponseEntity.ok().body(response);
        return dto.getMessage();
    }
}
