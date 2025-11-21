package com.todo.vo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="Todo")
public class TodoVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String message;
    private String ect;

    @Builder
    public TodoVo(String id, String message,String ect){
        this.id = id;
        this.message = message;
        this.ect = ect;
    }
}
