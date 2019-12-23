package com.learn.spring.springactuator.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.learn.spring.springactuator.model.TodoList;

@Repository
public interface TodoRepository extends CrudRepository<TodoList, Long>{

}
