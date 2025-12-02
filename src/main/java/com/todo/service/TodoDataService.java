package com.todo.service;

import com.todo.dto.toDoDto;
import com.todo.repository.TodoDataRepository;
import com.todo.vo.TodoVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class TodoDataService {
    private final TodoDataRepository repository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public long insertTodo(toDoDto dto) {
        TodoVo todoVo = TodoVo.builder()
                .message(dto.getMessage())
                .ect(dto.getEct())
                .done(dto.isDone())
                .build();
        return repository.insertTodo(todoVo);
    }

    public Boolean updateTodo(toDoDto dto) {
        TodoVo todoVo = TodoVo.builder()
                .message(dto.getMessage())
                .ect(dto.getEct())
                .done(dto.isDone())
                .build();
        long returnR = repository.updateTodo(todoVo);

        if (returnR > 0)
            return true;
        else
            return false;
    }


}
