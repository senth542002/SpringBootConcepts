package com.learn.spring.springactuator.dto;

import java.util.ArrayList;
import java.util.List;

public class TodoListDto {
	
	private String name;
	private List<TodoItemDto> items = new ArrayList<>();
	public TodoListDto(String name, List<TodoItemDto> items) {
		super();
		this.name = name;
		this.items = items;
	}
	public String getName() {
		return name;
	}
	public List<TodoItemDto> getItems() {
		return items;
	}
	
	public void addItem(TodoItemDto item) {
		this.items.add(item);
	}

}
