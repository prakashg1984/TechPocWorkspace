package com.test.hazelcast.hazelcastdemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IdGenerator;

@RestController
public class HelloController {

	@Autowired
	HazelcastInstance hzInstance;
	
	
    @RequestMapping("/")
    public String index() {
    	System.out.println("greetings");
    	IMap<Long,String> queueCountMap = hzInstance.getMap("queueCount");
    	IdGenerator idGenerator = hzInstance.getIdGenerator("newid");
    	for (int i = 0; i < 10; i++) {
    		queueCountMap.put(idGenerator.newId(), "message" + 1);
    	}
        return "Greetings from Spring Boot POST!";
    }
    
	
    @RequestMapping("/fetch")
    public String fetch() {
    	java.util.concurrent.locks.Lock lock = hzInstance.getLock("mylock");
    	boolean isLocked = false;
    	try {
    	  isLocked = lock.tryLock();
    	  System.out.println("isLocked :" + isLocked);
    	  
    	  if(isLocked) {
	    	  Thread.sleep(10000);
	    	  IMap<Long,String> queueCountMap = hzInstance.getMap("queueCount");
	    	  System.out.println(queueCountMap.values());
	    	  return "Fetch! "+ queueCountMap.values();
    	  }else {
    		  return "Fetch - Not Lock Owner! " ;
    	  }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(isLocked) {
    		lock.unlock();
    		}
    	}
    	return "Fetch - ERROR! " ;
    }

}