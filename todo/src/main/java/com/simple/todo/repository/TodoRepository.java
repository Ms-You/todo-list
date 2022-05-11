package com.simple.todo.repository;

import com.simple.todo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    List<TodoEntity> findByUserId(String userId);

// 혹은
//    @Query("select * from Todo t where t.userId = ?1")
//    List<TodoEntity> findByUserId(String userId);

}
