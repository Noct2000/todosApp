package com.example.todos.app.controller;

import com.example.todos.app.mapper.TodoMapper;
import com.example.todos.app.model.Todo;
import com.example.todos.app.model.dto.TodoRequestDto;
import com.example.todos.app.service.TodoService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodoController {
    private TodoService todoService;
    private TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping()
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/list-todos")
    public String getAllTodos(ModelMap model) {
        String username = getLoggedInUsername();
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @GetMapping("/add-todo")
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUsername();
        model.addAttribute("formAction", "/add-todo");
        TodoRequestDto todo = new TodoRequestDto(username, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "todo";
    }

    @PostMapping("/add-todo")
    @ResponseBody
    public ResponseEntity<Todo> addNewTodo(
            @Valid @RequestBody TodoRequestDto todoRequestDto
    ) {
        Todo todo = todoMapper.toModel(todoRequestDto);
        todo.setUsername(getLoggedInUsername());
        todoService.save(todo);
        return ResponseEntity.ok(todo);
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(@RequestParam Long id) {
        todoService.deleteById(id);
        return "redirect:list-todos";

    }

    @GetMapping("/update-todo")
    public String showUpdateTodoPage(@RequestParam Long id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        model.addAttribute("formAction", "/update-todo?id=" + id);
        return "todo";
    }

    @PostMapping("/update-todo")
    @ResponseBody
    public ResponseEntity<Todo> updateTodo(
            @Valid @RequestBody TodoRequestDto todoRequestDto,
            @RequestParam Long id
    ) {
        Todo todo = todoMapper.toModel(todoRequestDto);
        todo.setId(id);
        todo.setUsername(getLoggedInUsername());
        todoService.save(todo);
        return ResponseEntity.ok(todo);
    }

    private String getLoggedInUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
