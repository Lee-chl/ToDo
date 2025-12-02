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
public class toDoDto {
    private String id;
    private String message;
    private String ect;
    private boolean done;

    public toDoDto(final TodoVo todoVo) {
        this.id = todoVo.getId();
        this.message = todoVo.getMessage();
        this.ect = todoVo.getEct();
        this.done = todoVo.isDone();
    }

    public static TodoVo toVo(final toDoDto dto) {
        return TodoVo.builder()
                .id(dto.id)
                .message(dto.message)
                .ect(dto.ect)
                .done(dto.isDone())
                .build();
    }
}
