package com.learn.spring.springactuator.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.learn.spring.springactuator.dto.TodoItemDto;
import com.learn.spring.springactuator.dto.TodoListDto;
import com.learn.spring.springactuator.model.TodoItem;
import com.learn.spring.springactuator.model.TodoList;

@Component
public class TodoListConverter implements Converter<TodoList, TodoListDto>{
	
	private TodoItemConverter todoListConverter;
	
	@Autowired
	public TodoListConverter(TodoItemConverter todoListConverter) {
		this.todoListConverter = todoListConverter;
	}


	@Override
	public TodoListDto convert(TodoList todoList) {
		List<TodoItem> items = todoList.getItems();
		
		List<TodoItemDto> itemDtos = items.stream()
										  .map(todoListConverter::convert)
										  .collect(Collectors.toList());
		return new TodoListDto(todoList.getName(), itemDtos);
	}

}
