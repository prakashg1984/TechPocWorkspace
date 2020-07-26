package com.test.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
	@Value("${message}")
	String message;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from "+message;
    }
    
}
