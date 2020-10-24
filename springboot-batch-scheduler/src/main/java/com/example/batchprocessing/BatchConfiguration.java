package com.example.batchprocessing;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.processor.MyProcessor;
import com.example.processor.MyReader;
import com.example.processor.MyWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    @Qualifier("SimpleJobLauncher")
    private SimpleJobLauncher jobLauncher;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    @Qualifier("MyReader")
	public MyReader myReader;

	@Autowired
	@Qualifier("MyWriter")
	public MyWriter myWriter;
	
	@Autowired
	@Qualifier("MyProcessor")
	public MyProcessor myProcessor;
	
	@Autowired
	@Qualifier("processOrderJob")
	Job processOrderJob;
	
	@Autowired
	Job importUserJob;
	
    @Scheduled(cron = "0 * * * * *")
    public void perform() throws Exception {

        System.out.println("Job Started at :" + new Date());

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();

        JobExecution execution = jobLauncher.run(processOrderJob, param);

        System.out.println("Job finished with status :" + execution.getStatus());
        
        JobExecution execution2 = jobLauncher.run(importUserJob, param);

        System.out.println("Job finished with status :" + execution2.getStatus());
    }

    @Bean("processOrderJob")
    public Job processOrderJob() {
        return jobBuilderFactory.get("processOrderJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(processStep()).end().build();
    }

    @Bean
    public Step processStep() {
    	return stepBuilderFactory.get("processStep")
				.<String, String> chunk(1)
				.reader(myReader).processor(myProcessor)
				.writer(myWriter).build();
    }

   

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }

}
