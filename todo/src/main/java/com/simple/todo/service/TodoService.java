package com.simple.todo.service;

import com.simple.todo.model.TodoEntity;
import com.simple.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoService {

    @Autowired
    private final TodoRepository todoRepository;

    // 리스트 생성
    public List<TodoEntity> create(final TodoEntity entity){
        // 검증 로직
        validate(entity);

        todoRepository.save(entity);

        log.info("Entity id: {} is saved", entity.getId());

        return todoRepository.findByUserId(entity.getUserId());
    }

    private void validate(TodoEntity entity) {
        if (entity == null){
            log.warn("엔티티가 null");
            throw new RuntimeException("엔티티는 널이 될 수 없음");
        }

        if (entity.getUserId() == null){
            log.warn("알 수 없는 사용자");
            throw new RuntimeException("알 수 없는 사용자입니다.");
        }
    }

    // 리스트 불러오기
    public List<TodoEntity> retrieve(final String userId){
        return todoRepository.findByUserId(userId);
    }


}
