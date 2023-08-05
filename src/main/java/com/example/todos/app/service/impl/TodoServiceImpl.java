package com.example.todos.app.service.impl;

import com.example.todos.app.model.Todo;
import com.example.todos.app.repository.TodoRepository;
import com.example.todos.app.service.TodoService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No todo by id: " + id)
        );
    }
}
