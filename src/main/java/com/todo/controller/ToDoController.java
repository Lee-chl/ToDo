package com.todo.controller;

import com.todo.dto.ToDoDto;
import com.todo.service.TodoDataService;
import com.todo.service.TodoService;
import com.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class ToDoController {

    private final TodoService service;
    private final TodoDataService dataService;
    final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("select/todo")
    public ResponseEntity<List<TodoVo>> selectTodoList(@RequestBody ToDoDto dto) {
        List<TodoVo> resultData = service.getTodoList(dto);
        if (resultData == null) {
            log.error("no dto data");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultData);
    }

    @PostMapping("insert/todo")
    public ResponseEntity<String> insertTodo(@RequestBody ToDoDto dto) throws Exception {
        if (dto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Input data error");
        }
        String returnStr = "";
        long resultCount = dataService.insertTodo(dto);
        if (resultCount == 0 || resultCount == 1) {
            returnStr = "성공";
        } else
            returnStr = "실패";

        return ResponseEntity.ok(returnStr);
    }

    @PostMapping("update/todo")
    public ResponseEntity<String> updateTodo(@RequestBody ToDoDto dto) throws Exception {
        if (dto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("input data Error");
        }
        boolean resultCount = dataService.updateTodo(dto);
        if (resultCount)
            return ResponseEntity.ok("성공");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("실패");
    }
}
