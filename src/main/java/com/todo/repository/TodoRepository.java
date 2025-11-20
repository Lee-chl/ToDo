package com.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {
    private final JPAQueryFactory queryFactory;

//    public List<TodoVo> getList(TodoVo todo){
//        return queryFactory.selectFrom(todo)
//                .where()
//    }

}
