package com.todo.vo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserVo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id; //사용자에게 고유하게 부여되는 ID

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;  //사용자의 email , id와 같은 기능

    @Column(nullable = false)
    private String password;
}
