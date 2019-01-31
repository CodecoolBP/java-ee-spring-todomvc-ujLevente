package com.codecool.todoapp.config;

import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("production")
public class InitDataAdder {

    private TodoDao todoDao;

    @Autowired
    public InitDataAdder(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @PostConstruct
    private void addSampleData() {
        todoDao.add(new Todo("first TODO item"));
    }
}
