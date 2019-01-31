package com.codecool.todoapp.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Todo(String title) {
        this.title = title;
        this.status = Status.ACTIVE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

}
