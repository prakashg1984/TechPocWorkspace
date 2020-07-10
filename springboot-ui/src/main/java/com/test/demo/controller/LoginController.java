package com.test.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.demo.bo.User;
import com.test.demo.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user,Model model) {
		
		if(loginService.validateUser(user)) {
			model.addAttribute("message", "User "+user.getUserName()+" Logged In");
			return "main";
		}else {
			model.addAttribute("message", "Invalid password");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(@ModelAttribute User user,Model model) {
		model.addAttribute("message", "User logged out");
		return "login";
	}
}
