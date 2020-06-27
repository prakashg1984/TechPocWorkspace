package com.test.hazelnet.client.hazelcastdemoclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@RestController
public class HelloController {

	@Autowired
	HazelcastInstance hzInstance;
	
	
    @RequestMapping("/fetch")
    public String index() {
    	java.util.concurrent.locks.Lock lock = hzInstance.getLock("mylock");
    	boolean isLocked = false;
    	try {
    	  isLocked = lock.tryLock();
    	  System.out.println("isLocked :" + isLocked);
    	  
    	  if(isLocked) {
	    	  Thread.sleep(10000);
	    	  IMap<Long,String> queueCountMap = hzInstance.getMap("queueCount");
	    	  System.out.println(queueCountMap.values());
	    	  return "Client Fetch! "+ queueCountMap.values();
    	  }else {
    		  return "Client Fetch - Not Lock Owner! " ;
    	  }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(isLocked) {
    		lock.unlock();
    		}
    	}
    	return "Client Fetch - ERROR! " ;
    }

}