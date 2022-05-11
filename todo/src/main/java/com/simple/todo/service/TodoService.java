package com.simple.todo.service;

import com.simple.todo.model.TodoEntity;
import com.simple.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    // 리스트 불러오기
    public List<TodoEntity> retrieve(final String userId){
        return todoRepository.findByUserId(userId);
    }


    // 리스트 수정
    public List<TodoEntity> update(final TodoEntity entity){
        validate(entity);

        final Optional<TodoEntity> original = todoRepository.findById(entity.getId());

        original.ifPresent(todo -> {
            // 값이 존재하면 update
            todo.setTitle(entity.getTitle());
            todo.setDone((entity.isDone()));

            todoRepository.save(todo);
        });

        return retrieve(entity.getUserId());
    }


    // 리스트 삭제
    public List<TodoEntity> delete(final TodoEntity entity){
        validate(entity);

        try{
            // 엔티티 삭제
            todoRepository.delete(entity);

        } catch (Exception e){
            log.error("엔티티 삭제 에러 ", entity.getId(), e);
            throw new RuntimeException("엔티티 삭제 에러 " + entity.getId());
        }

        return retrieve(entity.getUserId());
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

}
