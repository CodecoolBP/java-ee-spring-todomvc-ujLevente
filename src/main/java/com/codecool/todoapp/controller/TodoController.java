package com.codecool.todoapp.controller;

import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.dao.TodoDao;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class TodoController {

    private TodoDao todoDao;

    @Autowired
    public TodoController(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    private static final String SUCCESS = "{\"success\":true}";

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo-title") String todoTitle) {
        Todo newTodo = new Todo(todoTitle);
        log.info("adding new todo: " + newTodo);
        todoDao.add(newTodo);
        return SUCCESS;
    }

    @PostMapping("/list")
    public List<Todo> getTodosByStatus(@RequestParam("status") String status) {
        List<Todo> todos = todoDao.ofStatus(status);
        log.info("getTodosByStatus, results: " + todos);

        return todos;
    }

    @DeleteMapping("/todos/completed")
    public String deleteCompleted() {
        log.info("delete completed todos");
        todoDao.removeCompleted();
        return SUCCESS;
    }

    @PutMapping("todos/toggle_all")
    public String toggleAllByStatus(@RequestParam("toggle-all") String complete) {
        // todo
        log.info("toggleing all by request paramter(" + complete + ")");
        todoDao.toggleAll(complete.equals("true"));
        return SUCCESS;
    }

    @DeleteMapping("/todos/{id}")
    public String removeTodoById(@PathVariable(value="id") Long id) {
        log.info("removing todo by id: " + id);
        todoDao.remove(id);
        return SUCCESS;
    }

    @PutMapping("/todos/{id}")
    public String updateTodoById(@PathVariable(value="id") Long id, @RequestParam("todo-title") String todoTitle) {
        log.info("updating todo by id: " + id + " to new title: " + todoTitle);
        todoDao.update(id, todoTitle);
        return SUCCESS;
    }

    @GetMapping("/todos/{id}")
    public String findById(@PathVariable(value="id") Long id) {
        log.info("find todo by id :" + id + "tod found: " + todoDao.find(id));
        return todoDao.find(id).getTitle();
    }

    @PutMapping("todos/{id}/toggle_status")
    public String toggleStatusById(@PathVariable(value="id") Long id, @RequestParam("status") String status) {
        log.info("updating todo by id: " + id + " and status: " + status + ", todo to update: " + todoDao.find(id));
        boolean completed = status.equals("true");
        todoDao.toggleStatus(id, completed);
        log.info("updatedt to: " + todoDao.find(id));
        return SUCCESS;
    }

}
