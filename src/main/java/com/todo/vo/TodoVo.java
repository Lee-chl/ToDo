package com.todo.vo;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Todo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String user_id;
    private String message;
    private String ect;
}
