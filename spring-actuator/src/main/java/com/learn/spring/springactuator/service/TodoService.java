package com.learn.spring.springactuator.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.spring.springactuator.converter.TodoListConverter;
import com.learn.spring.springactuator.dto.TodoListDto;
import com.learn.spring.springactuator.repository.TodoRepository;

@Service
public class TodoService {

	private TodoListConverter todoListConverter;
	private TodoRepository todoRepository;
	
	@Autowired
	public TodoService(TodoListConverter todoListConverter, TodoRepository todoRepository) {
		this.todoListConverter = todoListConverter;
		this.todoRepository = todoRepository;
	}
	
	public List<TodoListDto> getTodoLists() {
		return StreamSupport.stream(todoRepository.findAll().spliterator(), false)
				.map(todoListConverter::convert)
				.collect(Collectors.toList());
	}
}
