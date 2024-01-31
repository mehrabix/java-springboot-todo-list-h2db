package com.mehrabi.mehrabi.repository;

import com.mehrabi.mehrabi.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByTitle(String title);

    List<TodoEntity> findByCompletedTrue();

    List<TodoEntity> findByDescriptionContaining(String keyword);
}