package com.codecool.todoapp;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class BasicTodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicTodoListApplication.class, args);
    }

}
