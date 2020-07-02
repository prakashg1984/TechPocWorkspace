package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	static ApplicationContext context = null;
	public static void main(String[] args) {
		App app = new App();
		String[] springConfig  = 
			{	"spring/batch/config/database.xml", 
				"spring/batch/config/context.xml",
				"spring/batch/jobs/job-csv-db.xml", 
				"spring/batch/jobs/job-db-xml.xml" 
			};
		
		context = new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		app.runCsvToDbJob(jobLauncher);
		
		app.runDbToXmlJob(jobLauncher);

		System.out.println("Done");

	}
	
	private void runCsvToDbJob(JobLauncher jobLauncher) {
		MapJobRegistry mapJobRegistry = (MapJobRegistry) context.getBean("jobRegistry");
				
		//Job job = (Job) context.getBean("csvToDbJob");

		try {
			Job job = mapJobRegistry.getJob("csvToDbJob");
			
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void runDbToXmlJob(JobLauncher jobLauncher) {
		MapJobRegistry mapJobRegistry = (MapJobRegistry) context.getBean("jobRegistry");
		//Job job = (Job) context.getBean("xmlJob");

		try {
			Job job = mapJobRegistry.getJob("xmlJob");
			System.out.println(job.toString());

			JobParameters param = new JobParametersBuilder().addString("age", "20").toJobParameters();

			JobExecution execution = jobLauncher.run(job, param);
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
