package com.test.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.demo.bo.User;
import com.test.demo.service.LoginService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Value("${errormessage}")
	public String errormessage;
	
	private MeterRegistry meterRegistry;
	Counter validUserCounter;
	Counter inValidUserCounter;
	
    public LoginController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        
        validUserCounter = Counter.builder("user.login.attempt")
        		.tag("type", "Valid")
        		.description("Number of Valid and Invalid Login Attempt")
        		.register(this.meterRegistry);
        
        inValidUserCounter = Counter.builder("user.login.attempt")
        		.tag("type", "InValid")
        		.description("Number of Valid and Invalid Login Attempt")
        		.register(this.meterRegistry);

    }
    
	@GetMapping("/login")
	public String login(Model model,HttpSession session) {
		model.addAttribute("user", new User());
		if(session.getAttribute("userName") != null) {
			model.addAttribute("message", "User "+session.getAttribute("userName")+" Logged In");
			return "main";
		}
		return "login";
	}
	
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute @Valid User user, BindingResult bindingResult,Model model, HttpSession session) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errormessage", errormessage);
			return "login";
		}
		
		if(loginService.validateUser(user)) {
			validUserCounter.increment();
			session.setAttribute("userName", user.getUserName());
			model.addAttribute("message", "User "+user.getUserName()+" Logged In");
			return "main";
		}else {
			inValidUserCounter.increment();
			model.addAttribute("message", "Invalid password");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(@ModelAttribute User user,Model model,HttpSession session) {
		validUserCounter.increment(-1.0);
		model.addAttribute("message", "User "+session.getAttribute("userName") +" logged out");
		session.invalidate();
		return "login";
	}
}
