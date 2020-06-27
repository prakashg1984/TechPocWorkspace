package com.example.securingweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SampleController {

	  @GetMapping("/hello")
	  public String greetingForm(Model model) {
	    model.addAttribute("greeting", new Greeting());
	    return "hello";
	  }

	  @PostMapping("/hello")
	  public String greetingSubmit(@ModelAttribute Greeting greeting) {
	    return "outputresult";
	  }

}
