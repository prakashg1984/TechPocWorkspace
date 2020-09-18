package com.example.batchprocessing;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.bo.Person;
import com.example.processor.DummyItemProcessor;
import com.example.processor.MyProcessor;
import com.example.processor.MyReader;
import com.example.processor.MyWriter;
import com.example.processor.PersonItemProcessor;

@Configuration
//@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public 
	
	@Autowired
	public MyReader myReader;

	@Autowired
	public MyWriter myWriter;
	
	@Autowired
	public MyProcessor myProcessor;
	
	@Bean
	public MyReader myReader() {
		return new MyReader();
	}
	
	@Bean
	public MyWriter myWriter() {
		return new MyWriter();
	}
	
	@Bean
	public MyProcessor myProcessor() {
		return new MyProcessor();
	}
	
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}
	
	@Bean
	public DummyItemProcessor dummyProcessor() {
		return new DummyItemProcessor();
	}
	
	@Scheduled(fixedDelay = 5000)
    public void run(){
       LOG.info("Processing Scheduled run...");
       try
       {
          Job job = importUserJob();
          JobParameters jobParameters = new JobParameters();
          jobLauncher.run(job, jobParameters);
       }catch(Exception e){
          
       }
    }

	@Bean
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>()
			.name("personItemReader")
			.resource(new ClassPathResource("sample-data.csv"))
			.delimited()
			.names(new String[]{"firstName", "lastName"})
			.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
				setTargetType(Person.class);
			}})
			.build();
	}

	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Person>()
			.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
			.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
			.dataSource(dataSource)
			.build();
	}

	//Starting Point for a Job Execution - First Job
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1, Step step2) {
		return jobBuilderFactory.get("importUserJob")
			.incrementer(new RunIdIncrementer())
			.listener(listener)
			.flow(step1)
			.end()
			.build();
	}
	
	//Starting Point for a Job Execution - Second Job
	@Bean
	public Job importUserJob2(JobCompletionNotificationListener listener, Step step1, Step step2) {
		return jobBuilderFactory.get("importUserJob2")
			.incrementer(new RunIdIncrementer())
			.listener(listener)
			.flow(step2)
			.end()
			.build();
	}

	//Step That will be called from First Job
	@Bean
	public Step step1(JdbcBatchItemWriter<Person> writer) {
		return stepBuilderFactory.get("step1")
			.<Person, Person> chunk(10)
			.reader(reader())
			.processor(processor())
			//.processor(dummyProcessor())
			.writer(writer)
			.build();
	}
	
	//Step That will be called from Second Job
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<String, String> chunk(1)
				.reader(myReader).processor(myProcessor)
				.writer(myWriter).build();
	}
	
}
