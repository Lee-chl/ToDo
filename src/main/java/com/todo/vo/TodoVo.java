package com.todo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Todo")
public class TodoVo {
    @Id
    private String id;
    private String message;
    private String ect;
}
