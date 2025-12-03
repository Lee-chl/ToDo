package com.todo.controller;

import com.todo.dto.ResponseDTO;
import com.todo.dto.toDoDto;
import com.todo.service.TodoDataService;
import com.todo.service.TodoService;
import com.todo.service.TodoServiceNew;
import com.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class ToDoController {

    private final TodoService service;
    private final TodoDataService dataService;
    private final TodoServiceNew serviceNew;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final String temporaryUserId = "temporary-user";

    @PostMapping("select/todo")
    public ResponseEntity<List<TodoVo>> selectTodoList(@RequestBody toDoDto dto) {
        List<TodoVo> resultData = service.getTodoList(dto);
        if (resultData == null) {
            log.error("no dto data");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultData);
    }

    @PostMapping("insert/todo")
    public ResponseEntity<String> insertTodo(@RequestBody toDoDto dto) throws Exception {
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
    public ResponseEntity<String> updateTodo(@RequestBody toDoDto dto) throws Exception {
        if (dto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("input data Error");
        }
        boolean resultCount = dataService.updateTodo(dto);
        if (resultCount)
            return ResponseEntity.ok("성공");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("실패");
    }

    @PostMapping("create/todo")
    public ResponseEntity<?> createTodo(@RequestBody toDoDto dto) {
        try {
            // 엔티티로 변환
            TodoVo vo = toDoDto.toVo(dto);
            vo.setUser_id(temporaryUserId);
            vo.setId(null);
            //서비스 사용해 엔티티 생성
            List<TodoVo> entities = serviceNew.create(vo);
            log.info(vo.toString());
            // 엔티티 리스트를 DTO 리스트로 변환
            List<toDoDto> dtos = entities.stream().map(toDoDto::new).collect(Collectors.toList());
            //변환된 TodoDto 리스트를 이용해 초기화
            ResponseDTO<List<toDoDto>> response = ResponseDTO.<List<toDoDto>>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<String> response = ResponseDTO.<String>builder().data(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("get/todo")
    public ResponseEntity<?> retrieveTodoList() {
        // 서비스 메서드 retrieve() 메서드를 사용해 리스트 가져온다.
        List<TodoVo> entities = serviceNew.retrieveTodoList(temporaryUserId);
        //자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDto 리스트로 변환
        List<toDoDto> dtos = entities.stream().map(toDoDto::new).collect(Collectors.toList());
        // 변환된 TodoDTO 리스트를 이용해 ResponseDTO 초기화
        ResponseDTO<List<toDoDto>> response = ResponseDTO.<List<toDoDto>>builder().data(dtos).build();
        // ResponseDTO 리턴
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("put/todo")
    public ResponseEntity<?> putTodo(@RequestBody toDoDto dto) {
        TodoVo vo = toDoDto.toVo(dto);
        vo.setUser_id(temporaryUserId);
        List<TodoVo> entities = serviceNew.update(vo);
        List<toDoDto> dtos = entities.stream().map(toDoDto::new).collect(Collectors.toList());
        ResponseDTO<List<toDoDto>> response = ResponseDTO.<List<toDoDto>>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("delete/todo")
    public ResponseEntity<?> deleteTodo(@RequestBody toDoDto dto) {
        try {
            TodoVo vo = toDoDto.toVo(dto);
            vo.setUser_id(temporaryUserId);
            List<TodoVo> entities = serviceNew.delete(vo);
            List<toDoDto> dtos = entities.stream().map(toDoDto::new).collect(Collectors.toList());
            ResponseDTO<List<toDoDto>> response = ResponseDTO.<List<toDoDto>>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<String> response = ResponseDTO.<String>builder().data(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
