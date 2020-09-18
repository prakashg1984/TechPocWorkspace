package com.pg.controller;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView haloModelView(ModelMap modelMap){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);

		String formattedDate = dateFormat.format(date);
		
		ModelAndView mv = new ModelAndView("home") ;
		mv.addObject("serverTime", formattedDate);
		return mv;
	}

}
