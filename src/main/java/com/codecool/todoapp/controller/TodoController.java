package com.codecool.todoapp.controller;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.model.TodoDao;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class TodoController {

    private static final String SUCCESS = "{\"success\":true}";

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo-title") String todoTitle) {
        Todo newTodo = Todo.create(todoTitle);
        log.info("adding new todo: " + newTodo);
        TodoDao.add(newTodo);
        return SUCCESS;
    }

    @PostMapping("/list")
    public List<Todo> getTodosByStatus(@RequestParam("status") String status) {
        List<Todo> todos = TodoDao.ofStatus(status);
        log.info("getTodosByStatus: ");
        return todos;
    }

    @DeleteMapping("/todos/completed")
    public String deleteCompleted() {
        TodoDao.removeCompleted();
        return SUCCESS;
    }

    @PutMapping("todos/toggle_all")
    public String toggleAllByStatus(@RequestParam("toggle-all") String complete) {
        // todo
        TodoDao.toggleAll(complete.equals("true"));
        return SUCCESS;
    }

    @DeleteMapping("/todos/{id}")
    public String removeTodoById(@PathVariable(value="id") String id) {
        TodoDao.remove(id);
        return SUCCESS;
    }

    @PutMapping("/todos/{id}")
    public String updateTodoById(@PathVariable(value="id") String id, @RequestParam("todo-title") String todoTitle) {
        TodoDao.update(id, todoTitle);
        return SUCCESS;
    }

    @GetMapping("/todos/{id}")
    public String findById(@PathVariable(value="id") String id) {
        return TodoDao.find(id).getTitle();
    }

    @PutMapping("todos/{id}/toggle_status")
    public String toggleStatusById(@PathVariable(value="id") String id, @RequestParam("status") String status) {
        boolean completed = status.equals("true");
        TodoDao.toggleStatus(id, completed);
        return SUCCESS;
    }

}
