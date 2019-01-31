package com.codecool.todoapp.repositories.dao;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.TodoRepository;
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

        assertThat(todoRepository.findAll().get(0).getTitle()).isEqualTo(newTitle);
    }

    @Test
    public void offStatusTestWithDifferentArgumentsPassed() {
        Todo activeTodo = new Todo("active todo");
        Todo completedTodo = new Todo("completed todo");
        completedTodo.setStatus(Status.COMPLETE);

        todoRepository.save(activeTodo);
        todoRepository.save(completedTodo);

        assertThat(todoDao.ofStatus("COMPLETE")).containsOnly(completedTodo);
        assertThat(todoDao.ofStatus("complete")).containsOnly(completedTodo);
        assertThat(todoDao.ofStatus("ACTIVE")).containsOnly(activeTodo);
        assertThat(todoDao.ofStatus("active")).containsOnly(activeTodo);
        assertThat(todoDao.ofStatus("random string")).hasSize(2);
        assertThat(todoDao.ofStatus("")).hasSize(2);
        assertThat(todoDao.ofStatus(null)).hasSize(2);
    }

    @Test
    public void remove() {
        todoRepository.save(testTodo);
        assertThat(todoRepository.findAll()).hasSize(1);

        todoDao.remove(testTodo.getId());

        assertThat(todoRepository.findAll()).hasSize(0);
    }

    @Test
    public void deleteCompletedTodos() {
        testTodo.setStatus(Status.COMPLETE);
        todoRepository.save(testTodo);
        assertThat(todoRepository.findAll()).hasSize(1);

        todoDao.removeCompleted();

        assertThat(todoRepository.findAll()).hasSize(0);
    }

    @Test
    public void toggleStatusById() {
        todoRepository.save(testTodo);

        todoDao.toggleStatus(testTodo.getId(), true);
        assertThat(todoRepository.findAll().get(0).getStatus()).isEqualTo(Status.COMPLETE);

        todoDao.toggleStatus(testTodo.getId(), false);
        assertThat(todoRepository.findAll().get(0).getStatus()).isEqualTo(Status.ACTIVE);
    }

    @Test
    public void toggleAll() {
        todoRepository.save(testTodo);

        todoDao.toggleAll(false);
        assertThat(todoRepository.findAll().get(0).getStatus()).isEqualTo(Status.COMPLETE);

        todoDao.toggleAll(true);
        assertThat(todoRepository.findAll().get(0).getStatus()).isEqualTo(Status.ACTIVE);
    }


    @Test
    public void getAll() {
        todoRepository.save(testTodo);

        assertThat(todoDao.all()).hasSize(1);
    }
}