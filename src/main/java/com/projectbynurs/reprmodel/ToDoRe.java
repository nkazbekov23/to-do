package com.projectbynurs.reprmodel;

import com.projectbynurs.entity.ToDo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ToDoRe {

    private Integer id;

    @NotEmpty
    private String description;

    private String username;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    public ToDoRe() {}

    public ToDoRe(Integer id, @NotEmpty String description, String username, @NotNull LocalDate targetDate) {
        this.id = id;
        this.description = description;
        this.username = username;
        this.targetDate = targetDate;

    }

    public ToDoRe(ToDo toDo) {
        this.id = toDo.getId();
        this.description = toDo.getDescription();
        this.username = toDo.getUser().getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
