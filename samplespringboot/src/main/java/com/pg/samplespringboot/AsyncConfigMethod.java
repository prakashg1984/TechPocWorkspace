package com.pg.samplespringboot;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfigMethod {

	@Bean(name = "threadPoolExecutor")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ThreadPoolExecutor  threadPoolExecutor() {
		return new NamedThreadPoolExecutor(40, 40, 1000, TimeUnit.SECONDS,  "myThreadPoolTaskExecutorMethod");
	}

}
