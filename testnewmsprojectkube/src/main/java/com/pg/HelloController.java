package com.pg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
	@Value("${my.message}")
	private String myMessage;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot with message! "+myMessage;
    }
    
}
