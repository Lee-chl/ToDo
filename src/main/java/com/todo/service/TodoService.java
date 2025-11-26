package com.todo.service;

import com.todo.dto.ToDoDto;
import com.todo.repository.TodoRepository;
import com.todo.repository.TodoRepository_new;
import com.todo.vo.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository repository;
    TodoRepository_new repository_new;

    public List<TodoVo> getTodoList(ToDoDto todoDto) {
        return repository.getList(todoDto);
    }

    public String testService() {
        // 엔티티 생성
        TodoVo entity = TodoVo.builder().message("My first todo item").build();
        // 엔티티 저장
        repository_new.save(entity);
        //엔티티 검색
        TodoVo savedEntity = repository_new.findById(entity.getId()).get();
        return savedEntity.getMessage();
    }

}
