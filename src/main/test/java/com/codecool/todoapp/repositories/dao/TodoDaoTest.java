package com.codecool.todoapp.repositories.dao;

import com.codecool.todoapp.model.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class TodoDaoTest {


    private Todo testTodo;
    private static final String SAMPLE_TITLE = "sampe title";

    @Autowired
    private TodoDao todoDao;

    @Before
    public void beforeEach() {
        testTodo = Todo.create(SAMPLE_TITLE);
    }


}