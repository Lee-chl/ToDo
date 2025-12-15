package com.todo.repository;

import com.todo.vo.UserVo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVo, String> {
    UserVo findByEmail(String email);

    Boolean existsByEmail(String email);

    UserVo findByEmailAndPassword(String email, String passward);
}
