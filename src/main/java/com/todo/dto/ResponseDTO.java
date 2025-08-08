package com.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ResponseDTO<T> {
    private Integer status;
    private String message;
    private T data;
    private LocalDateTime timestamp = LocalDateTime.now();

    @Builder
    public ResponseDTO(String message,T data){
        this.status = HttpStatus.OK.value();
        this.message = message;
        this.data = data;
    }

    @Builder
    public ResponseDTO(Integer status,String message,T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        try{
            return HttpStatus.valueOf(status);
        }catch (IllegalArgumentException e){
            return HttpStatus.OK;
        }
    }
}
