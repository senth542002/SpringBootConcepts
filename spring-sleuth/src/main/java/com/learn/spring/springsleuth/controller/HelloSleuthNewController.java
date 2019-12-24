package com.learn.spring.springsleuth.controller;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.springsleuth.service.SleuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloSleuthNewController {
	
	@Autowired
	private SleuthService sleuthService;
	
	@Autowired
	private Executor executor;
	
	@GetMapping("/new-span")
	public String helloSleuthNewSpan() {
		log.info("New Span");
		sleuthService.doSomeWorkNewSpan();
		return "success";
	}
	
	@GetMapping("/new-thread")
	public String helloSleuthNewThread() {
		log.info("New Thread");
		
		Runnable runnable = () -> {
			try {
			Thread.sleep(1000L);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			log.info("Inside the new thread - with a new span");
		};
		
		executor.execute(runnable);
		
		log.info("I'm done - with the original span");
		return "Success";
	}

	@GetMapping("/async")
	public String helloSleuthAsync() {
		log.info("Before Async Call");
		sleuthService.asyncMethod();
		log.info("After Async Call");
		return "Success";
	}
}
