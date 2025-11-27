package com.todo.controller;

import com.todo.dto.ResponseDTO;
import com.todo.dto.ToDoDto;
import com.todo.service.TodoDataService;
import com.todo.service.TodoService;
import com.todo.service.TodoService_new;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class ToDoController {

    private final TodoService service;
    private final TodoDataService dataService;
    private final TodoService_new service_new;
    private final Logger log = LoggerFactory.getLogger(getClass());

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

    @PostMapping("create/todo")
    public ResponseEntity<?> createTodo(@RequestBody ToDoDto dto) {
        try {
            String temporaryUserId = "temporary-user";

            // 엔티티로 변환
            TodoVo vo = TodoVo.builder()
                    .id(temporaryUserId) //임시 사용자 아이디 설정
                    .message(dto.getMessage())
                    .ect(dto.getEct())
                    .build();
            //서비스 사용해 엔티티 생성
            List<TodoVo> entities = service_new.create(vo);
            // 엔티티 리스트를 DTO 리스트로 변환
            List<ToDoDto> dtos = entities.stream().map(ToDoDto::new).collect(Collectors.toList());
            //변환된 TodoDto 리스트를 이용해 초기화
            ResponseDTO<List<ToDoDto>> response = ResponseDTO.<List<ToDoDto>>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<String> response = ResponseDTO.<String>builder().data(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
