package com.todo.service;

import com.todo.dto.ToDoDto;
import com.todo.repository.TodoRepository;
import com.todo.vo.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository repository;

    public List<TodoVo> getTodoList(ToDoDto todoDto){
        return repository.getList(todoDto);
   }
}
