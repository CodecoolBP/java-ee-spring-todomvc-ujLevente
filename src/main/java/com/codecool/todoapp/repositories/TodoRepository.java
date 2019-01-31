package com.codecool.todoapp.repositories;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Todo t set title = :new_title where t.id = :id")
    void updateTodoTitleById(@Param("id") Long id, @Param("new_title") String newTitle);

    List<Todo> findTodosByStatus(Status status);
}
