package com.codecool.todoapp.controller;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.model.TodoDao;
import lombok.extern.java.Log;
import netscape.javascript.JSObject;
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
        log.info("getTodosByStatus, results: " + todos);

        return todos;
    }

    @DeleteMapping("/todos/completed")
    public String deleteCompleted() {
        log.info("delete completed todos");
        TodoDao.removeCompleted();
        return SUCCESS;
    }

    @PutMapping("todos/toggle_all")
    public String toggleAllByStatus(@RequestParam("toggle-all") String complete) {
        // todo
        log.info("toggleing all by request paramter(" + complete + ")");
        TodoDao.toggleAll(complete.equals("true"));
        return SUCCESS;
    }

    @DeleteMapping("/todos/{id}")
    public String removeTodoById(@PathVariable(value="id") String id) {
        log.info("removing todo by id: " + id);
        TodoDao.remove(id);
        return SUCCESS;
    }

    @PutMapping("/todos/{id}")
    public String updateTodoById(@PathVariable(value="id") String id, @RequestParam("todo-title") String todoTitle) {
        log.info("updating todo by id: " + id + " to new title: " + todoTitle);
        TodoDao.update(id, todoTitle);
        return SUCCESS;
    }

    @GetMapping("/todos/{id}")
    public String findById(@PathVariable(value="id") String id) {
        log.info("find todo by id :" + id + "tod found: " + TodoDao.find(id));
        return TodoDao.find(id).getTitle();
    }

    @PutMapping("todos/{id}/toggle_status")
    public String toggleStatusById(@PathVariable(value="id") String id, @RequestParam("status") String status) {
        log.info("updating todo by id: " + id + " and status: " + status + ", todo to update: " + TodoDao.find(id));
        boolean completed = status.equals("true");
        TodoDao.toggleStatus(id, completed);
        log.info("updatedt to: " + TodoDao.find(id));
        return SUCCESS;
    }

}
