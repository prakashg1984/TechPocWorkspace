package com.pg.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages="com.*")
public class AppConfig {

	
	@Bean
    public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		//driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/ticketexchange");
		driverManagerDataSource.setUrl("jdbc:sqlite:test.db");
		//driverManagerDataSource.setUsername("root");

        return driverManagerDataSource;
    }
	
}
