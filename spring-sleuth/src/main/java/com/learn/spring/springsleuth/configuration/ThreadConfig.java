package com.learn.spring.springsleuth.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableAsync
@EnableScheduling
public class ThreadConfig extends AsyncConfigurerSupport implements SchedulingConfigurer {
	
	@Autowired
	private BeanFactory beanFactory;
	
	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor =
				new ThreadPoolTaskExecutor();
		
		threadPoolTaskExecutor.setCorePoolSize(1);
		threadPoolTaskExecutor.setMaxPoolSize(1);
		threadPoolTaskExecutor.initialize();
		
		return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
	}
	@Bean
	@Override
	public Executor getAsyncExecutor() {
		
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		
		threadPoolTaskExecutor.setCorePoolSize(1);
		threadPoolTaskExecutor.setMaxPoolSize(1);
		threadPoolTaskExecutor.initialize();
		
		return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
	}
	
	@Bean(destroyMethod = "shutdown")
	public Executor getSchedulingExecutor() {
		return Executors.newScheduledThreadPool(1);
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(getSchedulingExecutor());
	}
	
	
	
}
