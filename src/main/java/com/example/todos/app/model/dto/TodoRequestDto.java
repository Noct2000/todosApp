package com.example.todos.app.model.dto;

import java.time.LocalDate;

public class TodoRequestDto {
    private String description;
    private LocalDate targetDate;
    private Boolean done;
    private String username;

    public TodoRequestDto(
            String description,
            LocalDate targetDate,
            Boolean done,
            String username
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
}
