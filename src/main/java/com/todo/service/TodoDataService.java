package com.todo.service;

import com.todo.dto.ToDoDto;
import com.todo.repository.TodoDataRepository;
import com.todo.vo.TodoVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoDataService {
    private final TodoDataRepository repository;

    public long insertTodo(ToDoDto dto) {
        TodoVo todoVo = TodoVo.builder()
                .id(dto.getId())
                .message(dto.getMessage())
                .ect(dto.getEct())
                .build();
        return repository.insertTodo(todoVo);
    }


}
