package com.codecool.todoapp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String title;
    private Status status;
    private static int _idCounter = 0;

    private Todo(String title, Status status) {
        this.title = title;
        this.status = status;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public static Todo create(String title) {
        _idCounter++;
        return new Todo(title, Status.ACTIVE);
    }
}
