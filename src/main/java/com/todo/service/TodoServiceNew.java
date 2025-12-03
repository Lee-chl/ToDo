package com.todo.service;

import com.todo.repository.TodoRepositoryNew;
import com.todo.vo.TodoVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoServiceNew {

    private final TodoRepositoryNew repositoryNew;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public List<TodoVo> create(final TodoVo vo) {
        validate(vo);
        repositoryNew.save(vo);
        log.info("Entity ID : {} is saved", vo.getUser_id());

        return repositoryNew.findByUserId(vo.getUser_id());
    }

    public List<TodoVo> retrieveTodoList(final String userId) {
        return repositoryNew.findByUserId(userId);
    }

    public List<TodoVo> update(final TodoVo vo) {
        validate(vo);
        final Optional<TodoVo> original = repositoryNew.findById(vo.getId());
        original.ifPresent(todo -> {
            //반환된 todovo가 존재하면 값을 새 vo 값으로 덮어 씌운다.
            todo.setMessage(vo.getMessage());
            todo.setEct(vo.getEct());
            todo.setDone(vo.isDone());

            //db 에 새 값 저장
            repositoryNew.save(todo);
        });

        //모든 사용자의 리스트 리턴
        return retrieveTodoList(vo.getUser_id());
    }

    public List<TodoVo> delete(final TodoVo vo) {
        validate(vo);
        try {
            repositoryNew.delete(vo);
        } catch (Exception e) {
            log.error("error deleting entity ", vo.getId(), e);
        }
        return retrieveTodoList(vo.getUser_id());
    }

    private void validate(final TodoVo vo) {
        if (vo == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if (vo.getUser_id() == null) {
            log.warn("Unknown User");
            throw new RuntimeException("Unknown User");
        }
    }
}
