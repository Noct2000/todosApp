package com.example.todos.app.model.dto;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class TodoRequestDto {
    private Long id;
    @Size(min = 5, message = "Enter at least 5 characters")
    private String description;
    private LocalDate targetDate;
    private Boolean done;
    private String username;

    public TodoRequestDto(
            String username,
            String description,
            LocalDate targetDate,
            Boolean done
    ) {
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
