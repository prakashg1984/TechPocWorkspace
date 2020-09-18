package com.pg.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pg.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	Logger logger = Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	LoginService haloService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String loginUser(@RequestParam("userName") String userName,
			@RequestParam("password") String password,ModelMap modelMap){
		logger.info("haloModelMap "+userName);
		haloService.invokeService(userName,password,modelMap);
		if(modelMap.containsKey("status") && modelMap.get("status").toString().equalsIgnoreCase("success")) {
			return "loginPage";
		}else {
			return "home";
		}
		
	}
	
	@RequestMapping(value = "/mv" , method = RequestMethod.GET)
	public ModelAndView haloModelView(ModelMap modelMap){
		ModelAndView mv = new ModelAndView("loginPage") ;
		mv.addObject("name", "PGMV");
		mv.addObject("emailAddress", "PGMV@gmail.com");
		return mv;
	}
	
	@RequestMapping(value = "/model" , method = RequestMethod.GET)
	public ModelAndView haloModel(Model model){
		model.addAttribute("name", "PGModel");
		model.addAttribute("emailAddress", "PGModel@gmail.com");
	
		return new ModelAndView("loginPage","model", model) ;
	}
	
	@RequestMapping(value="/loadCreateUser" , method = RequestMethod.POST)
	public ModelAndView loadCreateUserPage(ModelMap modelMap){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);

		ModelAndView mv = new ModelAndView("createUser") ;
		mv.addObject("serverTime", formattedDate);
		
		return mv;
	}
	
	@RequestMapping(value="/createUser" , method = RequestMethod.POST)
	public String createNewUser(@RequestParam Map<String, String> reqParam, ModelMap modelMap){
		logger.info("reqParam "+reqParam.toString());
		haloService.createNewUser(reqParam);
		modelMap.addAttribute("status", "Success");
		return "createUser";
	}
}
