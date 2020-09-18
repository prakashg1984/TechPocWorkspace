package com.att.pg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/index")
@RestController
public class HelloController {
	
	@Autowired
	SampleKafkaProducer kafkaProducer;
    
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String index(@RequestParam("message") String message) {
    	kafkaProducer.sendMessage(message);
        return "Greetings from Spring Boot! "+message;
    }
	
	
	@RequestMapping(value="/test/", method=RequestMethod.GET)
    public String index2() {
    	kafkaProducer.sendMessage("12345");
        return "Greetings from Spring Boot! 12345";
    }
	
	
	@RequestMapping(value="/path/{message}", method=RequestMethod.GET)
    public String index3(@PathVariable("message") String message) {
    	kafkaProducer.sendMessage(message);
        return "Greetings from Spring Boot! "+message;
    }
    
}
