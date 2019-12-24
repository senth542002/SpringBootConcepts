package com.learn.spring.springsleuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.springsleuth.service.SleuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SleuthController {
	
	@Autowired
	private SleuthService sleuthService;
	
	@GetMapping("/")
	public String helloSleuth() {	
		log.info("Hello Sleuth");
		sleuthService.doSomeWorkSameSpan();
		return "success";
	}

}
