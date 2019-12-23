package com.learn.spring.springactuator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.springactuator.dto.TodoListDto;
import com.learn.spring.springactuator.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {
	
	private TodoService todoService;

	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("/lists")
	public List<TodoListDto> getTodoLists() {
		return todoService.getTodoLists();
	}
	

}
