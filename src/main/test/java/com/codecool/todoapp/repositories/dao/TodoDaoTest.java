package com.codecool.todoapp.repositories.dao;

import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.TodoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TodoDaoTest {

    @Autowired
    private TodoRepository todoRepository;
    private Todo testTodo;
    private TodoDao todoDao;

    @PostConstruct
    public void init() {
        todoDao = new TodoDao(this.todoRepository);
    }

    @Before
    public void beforeEach() {
        testTodo = new Todo("sample_title");
    }

    @Test
    public void addOneSimple() {
        todoDao.add(testTodo);

        assertThat(todoRepository.findAll()).hasSize(1);
    }

    @Test
    public void getTodoById() {
        todoRepository.save(testTodo);

        assertThat(todoDao.find(testTodo.getId())).isEqualTo(testTodo);
    }

    @Test
    public void updateOneByTitleAndId() {
        todoRepository.save(testTodo);
        String newTitle = "changed title";

        todoDao.update(testTodo.getId(), newTitle);

        String title = todoRepository.findAll().get(0).getTitle();
        assertThat(title).isEqualTo(newTitle);
    }
}