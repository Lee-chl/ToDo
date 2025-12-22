package com.todo.service;

import com.todo.repository.UserRepository;
import com.todo.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public UserVo getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final UserVo orginalUser = userRepository.findByEmail(email);

        // matches 메서드 이용해 패스워드가 같은지 확인
        if (orginalUser != null && encoder.matches(password, orginalUser.getPassword())) {
            return orginalUser;
        }
        return null;
    }
}
