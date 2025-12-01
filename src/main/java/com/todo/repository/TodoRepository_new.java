package com.todo.repository;

import com.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository_new extends JpaRepository<TodoVo, String> {
    //?1 은 메서드의 매개변수의 순서 위치
    @Query(value = "select t from TodoVo t where t.user_id = ?1")
    List<TodoVo> findByUserId(String userId);
}
