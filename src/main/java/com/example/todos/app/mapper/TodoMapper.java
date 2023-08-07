package com.example.todos.app.mapper;

import com.example.todos.app.model.Todo;
import com.example.todos.app.model.dto.TodoRequestDto;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public Todo toModel(TodoRequestDto todoRequestDto) {
        return new Todo(
                todoRequestDto.getUsername(),
                todoRequestDto.getDescription(),
                todoRequestDto.getTargetDate(),
                todoRequestDto.getDone()
        );
    }
}
