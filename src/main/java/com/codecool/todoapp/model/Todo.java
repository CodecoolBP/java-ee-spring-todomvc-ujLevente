package com.codecool.todoapp.model;

import lombok.Data;

@Data
public class Todo {

    private String title;
    private String id;
    private Status status;
    private static int _idCounter = 0;

    private Todo(String title, String id, Status status) {
        this.title = title;
        this.id = id;
        this.status = status;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public static Todo create(String title) {
        _idCounter++;
        return new Todo(title, String.valueOf(_idCounter), Status.ACTIVE);
    }
}
