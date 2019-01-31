package com.codecool.todoapp.config;

import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.TodoRepository;
import com.codecool.todoapp.repositories.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("production")
public class InitDataAdder {

    private TodoDao todoDao;
    private TodoRepository todoRepository;

    @Autowired
    public InitDataAdder(TodoDao todoDao, TodoRepository todoRepository) {
        this.todoDao = todoDao;
        this.todoRepository = todoRepository;
    }

    @PostConstruct
    private void addSampleData() {
//        Todo sample = Todo.create("check");
//        sample.setStatus(Status.COMPLETE);
//        TodoDao.add(sample);
        todoDao.add(new Todo("first TODO item"));
//        TodoDao.add(Todo.create("second TODO item"));
//        TodoDao.add(Todo.create("third TODO item"));
//        log.info("sample data added");
    }
}
