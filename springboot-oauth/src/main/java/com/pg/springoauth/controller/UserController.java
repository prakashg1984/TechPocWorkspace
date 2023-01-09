package com.pg.springoauth.controller;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RestController
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
    	logger.info("Get User Principal : "+principal.getAttributes());
    	String name = StringUtils.isNotBlank(principal.getAttribute("login")) ? principal.getAttribute("login") : 
    		(StringUtils.isNotBlank(principal.getAttribute("name")) ? principal.getAttribute("name") : "UNKNOWN" );
        return Collections.singletonMap("name", name);
    }

}
