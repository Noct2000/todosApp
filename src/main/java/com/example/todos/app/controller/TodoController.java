package com.example.todos.app.controller;

import com.example.todos.app.mapper.TodoMapper;
import com.example.todos.app.model.Todo;
import com.example.todos.app.model.dto.TodoRequestDto;
import com.example.todos.app.service.TodoService;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;
    private TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    @ResponseBody
    public List<Todo> getTodos() {
        return todoService.findByUsername(getLoggedInUsername());
    }

    @PostMapping
    @ResponseBody
    public Todo addTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return todoService.save(todoMapper.toModel(todoRequestDto));
    }

    private String getLoggedInUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
