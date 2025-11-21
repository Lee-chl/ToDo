package com.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.dto.ToDoDto;
import com.todo.vo.QTodoVo;
import com.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {
    private final JPAQueryFactory queryFactory;

    public List<TodoVo> getList(ToDoDto todo){
        return queryFactory.selectFrom(QTodoVo.todoVo)
                .where(QTodoVo.todoVo.id.eq(todo.getId()))
                .fetch();
    }

}
