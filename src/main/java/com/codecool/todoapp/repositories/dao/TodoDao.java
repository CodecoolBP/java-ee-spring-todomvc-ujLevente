package com.codecool.todoapp.repositories.dao;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TodoDao {

//    private static final List<Todo> DATA = new ArrayList<>();

    private TodoRepository todoRepository;

    @Autowired
    public TodoDao(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void add(Todo todo) {
//        DATA.add(todo);
        todoRepository.save(todo);
    }

    public Todo find(Long id) {
//        return DATA.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        return todoRepository.getOne(id);
    }

    public void update(Long id, String title) {
//        find(id).setTitle(title);
        todoRepository.updateTodoTitleById(id, title);
    }

    public List<Todo> ofStatus(String statusString) {
        if (statusString == null)
            return todoRepository.findAll();

        statusString = statusString.toUpperCase();

        return Status.ACTIVE.toString().equals(statusString) || Status.COMPLETE.toString().equals(statusString) ?
            todoRepository.findTodosByStatus(Status.valueOf(statusString)) : todoRepository.findAll();
    }

    public void remove(Long id) {
//        DATA.remove(find(id));
        todoRepository.deleteById(id);
    }

    public void removeCompleted() {
//        ofStatus(Status.COMPLETE).forEach(t -> TodoDao.remove(t.getId()));
        todoRepository.deleteTodosByStatus(Status.COMPLETE);
    }

    public void toggleStatus(Long id, boolean isComplete) {
        Status newStatus = isComplete ? Status.ACTIVE : Status.COMPLETE;
        todoRepository.updateStatusById(id, newStatus);
    }

    public void toggleAll(boolean complete) {
//        TodoDao.all().forEach(t -> t.setStatus(complete ? Status.COMPLETE : Status.ACTIVE));
        Status from = complete ? Status.COMPLETE : Status.ACTIVE;
        Status to = complete ? Status.ACTIVE : Status.COMPLETE;
        todoRepository.updateStatusesFromTo(from, to);
    }

    public List<Todo> all() {
//        return DATA;
        return todoRepository.findAll();
    }
}
