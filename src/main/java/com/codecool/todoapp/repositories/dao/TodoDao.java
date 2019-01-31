package com.codecool.todoapp.repositories.dao;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//        return (statusString == null || statusString.isEmpty()) ? DATA : ofStatus(Status.valueOf(statusString.toUpperCase()));
        return null;
    }
//
//    public static List<Todo> ofStatus(Status status) {
//        return DATA.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
//    }
//
//    public static void remove(String id) {
//        DATA.remove(find(id));
//    }
//
//    public static void removeCompleted() {
//        ofStatus(Status.COMPLETE).forEach(t -> TodoDao.remove(t.getId()));
//    }
//
//    public static void toggleStatus(String id, boolean isComplete) {
//        Todo todo = find(id);
//        if (isComplete) {
//            todo.setStatus(Status.COMPLETE);
//        } else {
//            todo.setStatus(Status.ACTIVE);
//        }
//    }
//
//    public static void toggleAll(boolean complete) {
//        TodoDao.all().forEach(t -> t.setStatus(complete ? Status.COMPLETE : Status.ACTIVE));
//    }
//
//    public static List<Todo> all() {
//        return DATA;
//    }
}
