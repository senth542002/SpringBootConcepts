package com.learn.spring.springsleuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SleuthService {
	
	@Autowired
	private Tracer tracer;
	
	public void doSomeWorkSameSpan() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Doing Some work");
	}

	public void doSomeWorkNewSpan() {
		log.info("I'm the original span");
		
		Span newSpan = tracer.nextSpan().name("newSpan").start();
		
		try(SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
			Thread.sleep(1000L);
			log.info("I'm the new span doing some cool work that needs its own span");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			newSpan.finish();
		}
		
		log.info("I'm the original span");
	}
	
	@Async
	public void asyncMethod() {
		log.info("Start Async method");
		try {			
			Thread.sleep(1000L);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		log.info("End Async method");
	}
	
	@Scheduled(fixedDelay = 30000)
	public void scheduledWork() {
		log.info("Start some work from scheduled task");
		this.asyncMethod();
		log.info("End work from scheduled task");
	}

}
