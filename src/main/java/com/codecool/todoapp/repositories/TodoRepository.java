package com.codecool.todoapp.repositories;

import com.codecool.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
