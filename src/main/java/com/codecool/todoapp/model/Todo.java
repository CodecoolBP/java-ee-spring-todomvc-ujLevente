package com.codecool.todoapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String title;
    private Status status;
    private static int _idCounter = 0;

    public Todo(String title) {
        this.title = title;
        this.status = Status.ACTIVE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

}
