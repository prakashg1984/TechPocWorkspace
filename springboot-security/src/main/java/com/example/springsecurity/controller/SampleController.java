package com.example.springsecurity.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springsecurity.bo.Greeting;

@Controller
public class SampleController {

	  @GetMapping("/hello")
	  public String greetingForm(Model model) {
	    model.addAttribute("greeting", new Greeting());
	    return "hello";
	  }

	  @PostMapping("/hello")
	  public String greetingSubmit(@ModelAttribute @Valid Greeting greeting, BindingResult bindingResult) {
	  if(bindingResult.hasErrors()) {
			return "hello";
		}
	    return "outputresult";
	  }

}
