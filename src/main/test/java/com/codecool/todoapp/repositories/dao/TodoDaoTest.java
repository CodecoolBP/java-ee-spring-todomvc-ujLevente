package com.codecool.todoapp.repositories.dao;

import com.codecool.todoapp.BasicTodoListApplication;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.codecool.todoapp.repositories.dao.TodoDao;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoDaoTest {


    @Autowired
    private Todo testTodo;
//    private TodoDao todoDao = new TodoDao();

    @Autowired
    private TodoRepository todoRepository;

    @Before
    public void beforeEach() {
        testTodo = new Todo("sample_title");
    }

    @Test
    public void addOneSimple() {
//        todoDao.add(testTodo);
        todoRepository.save(testTodo);
        System.out.println(todoRepository.findAll());
        assertThat(todoRepository.findAll()).hasSize(1);
    }
}