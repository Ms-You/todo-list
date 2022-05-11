package com.simple.todo.controller;

import com.simple.todo.dto.ResponseDTO;
import com.simple.todo.dto.TodoDTO;
import com.simple.todo.model.TodoEntity;
import com.simple.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("todo")
@RequiredArgsConstructor
@RestController
public class TodoController {

    @Autowired
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try{
            String temporaryUserId = "temp-user";

            // 엔티티로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            entity.setId(null);

            // 임시 사용자 아이디 설정
            entity.setUserId(temporaryUserId);

            // Todo 엔티티 생성
            List<TodoEntity> entities = todoService.create(entity);

            // DTO로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }


    // 리스트 가져오기
    @GetMapping
    public ResponseEntity<?> retrieveTodoList(){
        String temporaryUserId = "temp-user";

        // userId로 Todo 리스트 가져옴
        List<TodoEntity> entities = todoService.retrieve(temporaryUserId);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }


    // 리스트 수정
    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
        String temporaryUserId = "temp-user";

        TodoEntity entity = TodoDTO.toEntity(dto);
        entity.setUserId(temporaryUserId);

        List<TodoEntity> entities = todoService.update(entity);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }


    // 리스트 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
        try{
            String temporaryUserId = "temp-user";

            TodoEntity entity = TodoDTO.toEntity(dto);
            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = todoService.delete(entity);

            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            
            return ResponseEntity.badRequest().body(response);
        }
    }



}
