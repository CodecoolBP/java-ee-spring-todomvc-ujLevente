package com.codecool.todoapp.repositories.dao;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoDao {

    private TodoRepository todoRepository;

    @Autowired
    public TodoDao(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void add(Todo todo) {
        todoRepository.save(todo);
    }

    public Todo find(Long id) {
        return todoRepository.getOne(id);
    }

    public void update(Long id, String title) {
        todoRepository.updateTodoTitleById(id, title);
    }

    public List<Todo> ofStatus(String statusString) {
        if (statusString == null)
            return todoRepository.findAll();

        statusString = statusString.toUpperCase();

        return Status.ACTIVE.toString().equals(statusString) || Status.COMPLETE.toString().equals(statusString) ?
            todoRepository.findTodosByStatusOrderByTitle(Status.valueOf(statusString)) :
            todoRepository.findAll(new Sort(Sort.Direction.DESC, "title"));
    }

    public void remove(Long id) {
        todoRepository.deleteById(id);
    }

    public void removeCompleted() {
        todoRepository.deleteTodosByStatus(Status.COMPLETE);
    }

    public void toggleStatus(Long id, boolean isComplete) {
        Status newStatus = isComplete ? Status.COMPLETE : Status.ACTIVE;
        todoRepository.updateStatusById(id, newStatus);
    }

    public void toggleAll(boolean complete) {
        todoRepository.updateAllStatuses(complete ? Status.COMPLETE : Status.ACTIVE);
    }

    public List<Todo> all() {
        return todoRepository.findAll();
    }
}
