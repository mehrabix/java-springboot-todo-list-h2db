package com.mehrabi.mehrabi.repository;

import com.mehrabi.mehrabi.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByTitle(String title);

    List<TodoEntity> findByCompletedTrue();

    List<TodoEntity> findByDescriptionContaining(String keyword);

    Page<TodoEntity> findAllByOrderByTitleAsc(Pageable pageable);

    Page<TodoEntity> findAllByOrderByTitleDesc(Pageable pageable);

    Page<TodoEntity> findAllByOrderByCompletedAsc(Pageable pageable);

    Page<TodoEntity> findAllByOrderByCompletedDesc(Pageable pageable);

    Page<TodoEntity> findByTitleContainingOrderByTitleAsc(String title, Pageable pageable);

    Page<TodoEntity> findByTitleContainingOrderByTitleDesc(String title, Pageable pageable);

    Page<TodoEntity> findByDescriptionContainingOrderByTitleAsc(String keyword, Pageable pageable);

    Page<TodoEntity> findByDescriptionContainingOrderByTitleDesc(String keyword, Pageable pageable);

    Page<TodoEntity> findByCompletedOrderByTitleAsc(boolean completed, Pageable pageable);

    Page<TodoEntity> findByCompletedOrderByTitleDesc(boolean completed, Pageable pageable);

}