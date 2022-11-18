package com.spring.azure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.azure.service.QueueMasterService;
import com.spring.azure.util.SearchCriteria;


@RestController
@RequestMapping("/v1")
public class QueueMasterController {

	@Autowired
	QueueMasterService queueMasterService;
	
	@RequestMapping(value = "/queue/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getQueueById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(queueMasterService.getQueuesById(id));
	}


	@RequestMapping(value = "/queues", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getAllQueues() {
		return ResponseEntity.status(HttpStatus.OK).body(queueMasterService.getAllQueues());
	}
	
	@RequestMapping(value = "/queue", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> searchQueues(@RequestParam(required = false) SearchCriteria searchString) {
		return ResponseEntity.status(HttpStatus.OK).body(queueMasterService.searchQueues(searchString));
	}

	@RequestMapping(value = "/queue", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> insertQueue(@RequestBody String queueMaster) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(queueMasterService.insertQueueMaster(queueMaster));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/queue", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateQueue(@RequestBody String queueMaster) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(queueMasterService.updateQueueMaster(queueMaster));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
