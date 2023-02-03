package com.pg.main;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.PreUndeployEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages="com.pg")
@EnableKafka
@EnableProcessApplication("mySimpleApplication")
public class SimpleApplication  {

/*  boolean processApplicationStopped;
*/
  public static void main(final String... args) throws Exception {
    SpringApplication.run(SimpleApplication.class, args);
  }

  /*private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private HistoryService historyService;

  @Autowired
  private Showcase showcase;

  @Autowired
  private TaskService taskService;
  
  @Value("${org.camunda.bpm.spring.boot.starter.example.simple.SimpleApplication.exitWhenFinished:true}")
  private boolean exitWhenFinished;

  @EventListener
  public void onPostDeploy(PostDeployEvent event) {
    logger.info("postDeploy: {}", event);
  }

  @EventListener
  public void onPreUndeploy(PreUndeployEvent event) {
    logger.info("preUndeploy: {}", event);
    processApplicationStopped = true;
  }


  @Scheduled(fixedDelay = 1500L)
  public void exitApplicationWhenProcessIsFinished() {
    Assert.isTrue(!((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration()).isDbMetricsReporterActivate());

    String processInstanceId = showcase.getProcessInstanceId();

    if (processInstanceId == null) {
      logger.info("processInstance not yet started!");
      return;
    }

    if (isProcessInstanceFinished()) {
      logger.info("processinstance ended!");

      if (exitWhenFinished) {
        jobExecutor.shutdown();
        SpringApplication.exit(context, () -> 0);
      }
      return;
    }
    logger.info("processInstance not yet ended!");
  }

  public boolean isProcessInstanceFinished() {
    final HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
        .processInstanceId(showcase.getProcessInstanceId()).singleResult();

    return historicProcessInstance != null && historicProcessInstance.getEndTime() != null;
  }

  @Override
  public void run(String... strings) throws Exception {
    logger.info("CommandLineRunner#run() - {}", ToStringBuilder.reflectionToString(camundaBpmProperties.getApplication(), ToStringStyle.SHORT_PREFIX_STYLE));
  }
  
  
  @Scheduled(fixedDelay = 60000L)
  public void exitApplicationWhenProcessIsFinished() {
    String processInstanceId = showcase.getProcessInstanceId();

    if (processInstanceId == null) {
      logger.info("processInstance not yet started!");
      return;
    }

    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    logger.info("task.getId(): {}", task.getId());
    taskService.complete(task.getId());
    logger.info("completed task: {}", task);
    
    logger.info("processInstance not yet ended!");
  }*/
}
