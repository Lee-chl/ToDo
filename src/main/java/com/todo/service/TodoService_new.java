package com.todo.service;

import com.todo.repository.TodoRepository_new;
import com.todo.vo.TodoVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService_new {

    private final TodoRepository_new repository_new;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public List<TodoVo> create(final TodoVo vo) {
        validate(vo);
        repository_new.save(vo);
        log.info("Entity ID : {} is saved", vo.getId());

        return repository_new.findByUserId(vo.getId());
    }

    private void validate(final TodoVo vo) {
        if (vo == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if (vo.getId() == null) {
            log.warn("Unknown User");
            throw new RuntimeException("Unknown User");
        }
    }
}
