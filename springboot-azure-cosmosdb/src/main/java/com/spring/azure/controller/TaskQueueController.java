package com.spring.azure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.azure.service.TaskQueueService;


@RestController
@RequestMapping("/v1")
public class TaskQueueController {

	@Autowired
	TaskQueueService taskQueueService;
	
	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getTaskById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(taskQueueService.getTaskById(id));
	}


	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getAllTasks() {
		return ResponseEntity.status(HttpStatus.OK).body(taskQueueService.getAllTasks());
	}

	@RequestMapping(value = "/task", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> insertTask(@RequestBody String task) {
		return ResponseEntity.status(HttpStatus.OK).body(taskQueueService.insertTask(task));
	}

	@RequestMapping(value = "/task", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateTask(@RequestBody String task) {
		return ResponseEntity.status(HttpStatus.OK).body(taskQueueService.updateTask(task));
	}
}
