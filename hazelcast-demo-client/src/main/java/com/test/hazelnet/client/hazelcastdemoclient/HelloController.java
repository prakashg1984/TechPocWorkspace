package com.test.hazelnet.client.hazelcastdemoclient;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@RestController
public class HelloController {

	@Autowired
	HazelcastInstance hzInstance;
	
	
	@RequestMapping(value ="/map/{data}", method=RequestMethod.GET)
    public String index(@PathVariable String data) {
    	System.out.println("greetings");
    	IMap<Long,String> queueCountMap = hzInstance.getMap("My-Map");
    	//IdGenerator idGenerator = hzInstance.getIdGenerator("newid");
		/*
		 * for (int i = 0; i < 10; i++) {
		 * queueCountMap.put(Long.valueOf(queueCountMap.size()+1), "message" +
		 * queueCountMap.size()); }
		 */
    	queueCountMap.put(Long.valueOf(queueCountMap.size()+1),data);
        return "Greetings from Spring Boot POST!";
    }
    
	
    @RequestMapping(value ="/fetch" , method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> fetch() {
    	java.util.concurrent.locks.Lock lock = hzInstance.getLock("mylock");
    	boolean isLocked = false;
    	try {
    	  isLocked = lock.tryLock();
    	  System.out.println("isLocked :" + isLocked);
    	  
    	  if(isLocked) {
	    	  Thread.sleep(10000);
	    	  IMap<Long,String> queueCountMap = hzInstance.getMap("My-Map");
	    	  System.out.println(queueCountMap.values());
	    	  return ResponseEntity.status(HttpStatus.OK).body(queueCountMap);
    	  }else {
    		  return ResponseEntity.status(HttpStatus.CONFLICT).body("Not Lock Owner");
    	  }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(isLocked) {
    		lock.unlock();
    		}
    	}
    	return ResponseEntity.status(HttpStatus.CONFLICT).body("Error");
    }
    
    @RequestMapping(value="/set" ,method = RequestMethod.POST )
    @ResponseBody
    public ResponseEntity<Object> insertSet(@RequestBody Map<String,String> data) {
    	
    	Set<String> set = hzInstance.getSet( "My-Name-Set" );
    	set.add(data.get("name"));
    	
    	return ResponseEntity.status(HttpStatus.OK).body(set);
    }
    
    @RequestMapping(value="/set" ,method = RequestMethod.GET )
    @ResponseBody
    public ResponseEntity<Object> fetchSet() {
    	Set<String> set = hzInstance.getSet( "My-Name-Set" );
    	
    	return ResponseEntity.status(HttpStatus.OK).body(set);
    }

}