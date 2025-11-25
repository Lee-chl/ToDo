package com.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.vo.QTodoVo;
import com.todo.vo.TodoVo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoDataRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public long insertTodo(TodoVo todoVo) {
        return entityManager.createNativeQuery("INSERT IGNORE INTO todo(id,message,ect) VALUES(?,?,?)")
                .setParameter(1, todoVo.getId())
                .setParameter(2, todoVo.getMessage())
                .setParameter(3, todoVo.getEct())
                .executeUpdate();
    }

    public long updateTodo(TodoVo todoVo) {
        return entityManager.createNativeQuery("UPDATE todo SET message = ? , ect = ? where id=?")
                .setParameter(1, todoVo.getMessage())
                .setParameter(2, todoVo.getEct())
                .setParameter(3, todoVo.getId())
                .executeUpdate();
    }


}
