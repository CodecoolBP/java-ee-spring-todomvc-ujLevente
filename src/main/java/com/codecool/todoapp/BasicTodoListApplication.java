package com.codecool.todoapp;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.TodoRepository;
import com.codecool.todoapp.repositories.dao.TodoDao;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Log
@SpringBootApplication
public class BasicTodoListApplication {

    private TodoDao todoDao = new TodoDao();

    @Autowired
    private TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(BasicTodoListApplication.class, args);
    }

    @PostConstruct
    private void addSampleData() {
//        Todo sample = Todo.create("check");
//        sample.setStatus(Status.COMPLETE);
//        TodoDao.add(sample);
        todoRepository.save(new Todo("first TODO item"));
        System.out.println(todoRepository.findAll());
//        TodoDao.add(Todo.create("second TODO item"));
//        TodoDao.add(Todo.create("third TODO item"));
//        log.info("sample data added");
    }

}
