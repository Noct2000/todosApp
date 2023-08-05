package com.example.todos.app.service;

import com.example.todos.app.model.Todo;
import java.util.List;

public interface TodoService {
    List<Todo> findByUsername(String username);

    Todo save(Todo todo);

    void deleteById(Long id);

    Todo findById(Long id);

}
