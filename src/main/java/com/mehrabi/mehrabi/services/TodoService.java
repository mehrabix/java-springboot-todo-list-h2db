package com.mehrabi.mehrabi.services;
import com.mehrabi.mehrabi.entities.TodoEntity;
import com.mehrabi.mehrabi.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoEntity> getAllTodos(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return todoRepository.findAll(pageable).getContent();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all todos", e);
            return List.of();
        }
    }

    public Optional<TodoEntity> getTodoById(Long id) {
        try {
            return todoRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving todo by ID: {}", id, e);
            return Optional.empty();
        }
    }

    public TodoEntity addTodo(TodoEntity todo) {
        try {
            return todoRepository.save(todo);
        } catch (Exception e) {
            logger.error("Error occurred while adding a todo: {}", todo, e);
            return null;
        }
    }

    public TodoEntity updateTodo(Long id, TodoEntity updatedTodo) {
        try {
            if (todoRepository.existsById(id)) {
                updatedTodo.setId(id);
                return todoRepository.save(updatedTodo);
            }
            return null;
        } catch (Exception e) {
            logger.error("Error occurred while updating todo with ID: {}", id, e);
            return null;
        }
    }

    public void deleteTodo(Long id) {
        try {
            todoRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting todo with ID: {}", id, e);
        }
    }
}
