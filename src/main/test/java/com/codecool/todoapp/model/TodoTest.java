package com.codecool.todoapp.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class TodoTest {

    private Todo testTodo;
    private static final String SAMPLE_TITLE = "sampe title";

    @Before
    public void beforeEach() {
        testTodo = Todo.create(SAMPLE_TITLE);
    }

    @Test
    public void sanityCheck() {
        assertThat(testTodo).isNotNull();
    }

    @Test
    public void testFields() {
        assertThat(testTodo.getTitle().equals(SAMPLE_TITLE));
        assertThat(testTodo.getId().equals("1"));
        assertThat(testTodo.toString()).isNotNull();
    }


}