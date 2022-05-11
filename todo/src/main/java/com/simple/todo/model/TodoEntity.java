package com.simple.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TodoEntity {

    private String id;
    private String userId;  // 사용자 아이디
    private String title;   // 리스트 제목
    private boolean done;   // 완료 여부

}
