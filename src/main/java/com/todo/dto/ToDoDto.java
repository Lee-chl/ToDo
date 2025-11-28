package com.todo.dto;

import com.todo.vo.TodoVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto {
    private String id;
    private String user_id;
    private String message;
    private String ect;

    public ToDoDto(final TodoVo todoVo) {
        this.id = todoVo.getId();
        this.message = todoVo.getMessage();
        this.ect = todoVo.getEct();
    }
}
