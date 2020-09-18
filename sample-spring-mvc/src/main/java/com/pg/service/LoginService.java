package com.pg.service;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.pg.dao.LoginDao;

@Service
public class LoginService {

	Logger logger = Logger.getLogger(LoginService.class.getName());
	
	@Autowired
	LoginDao loginDao;
	
	public void invokeService(String userName,String password,ModelMap modelMap) {
		Map resultMap = loginDao.checkUser(userName, password);
		
		if(null != resultMap) {
			modelMap.addAttribute("name", userName);
			modelMap.addAttribute("emailAddress", resultMap.get("email_address"));
			modelMap.addAttribute("status", "success");
		}else {
			modelMap.addAttribute("status", "failed");
		}
		
	}
	
	public void createNewUser(Map<String, String> reqParam) {
		String userName = reqParam.get("userName");
		String emailAddress = reqParam.get("emailAddress");
		String password = userName+"@123";
		
		loginDao.createUser(userName, password, emailAddress);
	}
}
