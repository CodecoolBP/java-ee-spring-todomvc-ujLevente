package com.codecool.todoapp;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Log
@SpringBootApplication
public class BasicTodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicTodoListApplication.class, args);
    }

    @PostConstruct
    private static void addSampleData() {
//        Todo sample = Todo.create("check");
//        sample.setStatus(Status.COMPLETE);
//        TodoDao.add(sample);
//        TodoDao.add(Todo.create("first TODO item"));
//        TodoDao.add(Todo.create("second TODO item"));
//        TodoDao.add(Todo.create("third TODO item"));
//        log.info("sample data added");
    }

}
