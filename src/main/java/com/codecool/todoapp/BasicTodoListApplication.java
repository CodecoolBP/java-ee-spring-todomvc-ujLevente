package com.codecool.todoapp;

import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.model.TodoDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicTodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicTodoListApplication.class, args);
    }

    private static void addSampleData() {
        TodoDao.add(Todo.create("first TODO item"));
        TodoDao.add(Todo.create("second TODO item"));
        TodoDao.add(Todo.create("third TODO item"));
    }
}
