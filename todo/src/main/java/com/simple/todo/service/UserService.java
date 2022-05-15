package com.simple.todo.service;

import com.simple.todo.model.UserEntity;
import com.simple.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity) {
        validation(userEntity);

        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String email, final String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    // 이메일 검증
    private void validation(UserEntity userEntity) {
        if (userEntity == null || userEntity.getEmail() == null) {
            throw new RuntimeException("유효하지 않은 값");
        }
        final String email = userEntity.getEmail();

        if (userRepository.existsByEmail(email)) {
            log.warn("이미 존재하는 이메일 {} ", email);
            throw new RuntimeException("이미 존재하는 이메일");
        }
    }

}
