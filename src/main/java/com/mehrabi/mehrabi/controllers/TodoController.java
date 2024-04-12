package com.mehrabi.mehrabi.controllers;

import com.mehrabi.mehrabi.entities.TodoEntity;
import com.mehrabi.mehrabi.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(TodoService.class);

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TodoEntity> getTodoById(@PathVariable Long id) {
        Optional<TodoEntity> todo = todoService.getTodoById(id);
        return todo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<TodoEntity> addTodo(@RequestBody TodoEntity todo) {
        TodoEntity addedTodo = todoService.addTodo(todo);
        return new ResponseEntity<>(addedTodo, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoEntity> updateTodo(@PathVariable Long id, @RequestBody TodoEntity updatedTodo) {
        TodoEntity todo = todoService.updateTodo(id, updatedTodo);
        return todo != null
                ? new ResponseEntity<>(todo, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllTodos(
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "10") int take,
            @RequestParam(defaultValue = "10") int pageSize) {
        try {
            int page = skip / pageSize;
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<TodoEntity> todoPage = todoService.getAllTodos(pageable);

            List<TodoEntity> todos = todoPage.getContent();
            long totalItems = todoPage.getTotalElements();
            int totalPages = todoPage.getTotalPages();

            Map<String, Object> response = new HashMap<>();
            response.put("items", todos);
            response.put("take", take);
            response.put("skip", skip);
            response.put("totalPage", totalPages);
            response.put("totalItems", totalItems);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all todos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bulk-delete")
    public ResponseEntity<Void> deleteTodos(@RequestBody List<Long> ids) {
        todoService.bulkDeleteTodos(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
