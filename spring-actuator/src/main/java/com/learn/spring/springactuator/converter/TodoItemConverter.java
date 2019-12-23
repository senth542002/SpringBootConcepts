package com.learn.spring.springactuator.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.learn.spring.springactuator.dto.TodoItemDto;
import com.learn.spring.springactuator.model.TodoItem;

@Component
public class TodoItemConverter implements Converter<TodoItem, TodoItemDto>{

	@Override
	public TodoItemDto convert(TodoItem item) {
		return new TodoItemDto(item.getItem(), item.isComplete());
	}

	
}
