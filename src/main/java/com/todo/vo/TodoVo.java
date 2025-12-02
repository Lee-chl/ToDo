package com.todo.vo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "Todo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoVo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String user_id;
    private String message;
    private String ect;
    private boolean done;
}
