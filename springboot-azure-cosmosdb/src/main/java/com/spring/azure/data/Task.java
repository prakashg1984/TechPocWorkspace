/*
 * package com.spring.azure.data;
 * 
 * import org.springframework.data.annotation.Id;
 * 
 * import com.azure.spring.data.cosmos.core.mapping.Container;
 * 
 * @Container(containerName = "OCE_TASK") public class Task {
 * 
 * @Id private String taskId; private String customerOrderNumber; private String
 * channel; private String taskStatus;
 * 
 * public Task() {
 * 
 * }
 * 
 * public Task(String taskId, String customerOrderNumber, String channel, String
 * taskStatus) { super(); this.taskId = taskId; this.customerOrderNumber =
 * customerOrderNumber; this.channel = channel; this.taskStatus = taskStatus; }
 * 
 * public String getTaskId() { return taskId; }
 * 
 * public void setTaskId(String taskId) { this.taskId = taskId; }
 * 
 * public String getCustomerOrderNumber() { return customerOrderNumber; }
 * 
 * public void setCustomerOrderNumber(String customerOrderNumber) {
 * this.customerOrderNumber = customerOrderNumber; }
 * 
 * public String getChannel() { return channel; }
 * 
 * public void setChannel(String channel) { this.channel = channel; }
 * 
 * public String getTaskStatus() { return taskStatus; }
 * 
 * public void setTaskStatus(String taskStatus) { this.taskStatus = taskStatus;
 * }
 * 
 * @Override public String toString() { return "Task [taskId=" + taskId +
 * ", customerOrderNumber=" + customerOrderNumber + ", channel=" + channel +
 * ", taskStatus=" + taskStatus + "]"; }
 * 
 * 
 * }
 */