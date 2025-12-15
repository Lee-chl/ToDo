package com.todo.service;

import com.todo.repository.UserRepository;
import com.todo.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    public UserVo create(final UserVo userVo) {
        if (userVo == null || userVo.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String email = userVo.getEmail();
        if (userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(userVo);
    }

    public UserVo getByCredentials(final String email, final String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
