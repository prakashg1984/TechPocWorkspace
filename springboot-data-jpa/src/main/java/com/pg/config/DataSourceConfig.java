package com.pg.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

@Configuration
public class DataSourceConfig {

	@Value("${my.sql.sequence}")
	public String mySequence;
	
	@Bean(name = "myDataSource")
	@Primary
	@ConfigurationProperties(prefix ="spring.datasource.mydb")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "myJdbcTemplate")
	public JdbcTemplate myJdbcTemplate(@Qualifier("myDataSource") DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
	
	@Bean(name = "myNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate myNamedParameterJdbcTemplate() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
		return namedParameterJdbcTemplate;
	}
	
	@Bean(name = "mySampleSequence")
	public OracleSequenceMaxValueIncrementer getMySequence(@Qualifier("myDataSource") DataSource dataSource) {
		OracleSequenceMaxValueIncrementer seq = new OracleSequenceMaxValueIncrementer();
		seq.setDataSource(dataSource);
		seq.setIncrementerName(mySequence);
		
		return seq;
	}
}
