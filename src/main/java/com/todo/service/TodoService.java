package com.todo.service;

import com.todo.dto.toDoDto;
import com.todo.repository.TodoRepository;
import com.todo.repository.TodoRepositoryNew;
import com.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;
    private final TodoRepositoryNew repositoryNew;

    public List<TodoVo> getTodoList(toDoDto todoDto) {
        return repository.getList(todoDto);
    }

    public String testService() {
        // 엔티티 생성
        TodoVo entity = TodoVo.builder().message("My first todo item").build();
        // 엔티티 저장
        repositoryNew.save(entity);
        //엔티티 검색
        TodoVo savedEntity = repositoryNew.findById(entity.getUser_id()).get();
        return savedEntity.getMessage();
    }

}
