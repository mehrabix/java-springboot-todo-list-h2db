package com.mehrabi.mehrabi.controllers;
import com.mehrabi.mehrabi.entities.TodoEntity;
import com.mehrabi.mehrabi.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoEntity> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoEntity> getTodoById(@PathVariable Long id) {
        Optional<TodoEntity> todo = todoService.getTodoById(id);
        return todo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<TodoEntity> addTodo(@RequestBody TodoEntity todo) {
        TodoEntity addedTodo = todoService.addTodo(todo);
        return new ResponseEntity<>(addedTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoEntity> updateTodo(@PathVariable Long id, @RequestBody TodoEntity updatedTodo) {
        TodoEntity todo = todoService.updateTodo(id, updatedTodo);
        return todo != null
                ? new ResponseEntity<>(todo, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
