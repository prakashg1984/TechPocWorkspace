package com.pg.springoauth.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.oauth2.sdk.util.StringUtils;

@SuppressWarnings("deprecation")
@RestController
public class UserController {

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
    	System.out.println("principal "+principal.getAttributes());
    	String name = StringUtils.isNotBlank(principal.getAttribute("login")) ? principal.getAttribute("login") : 
    		(StringUtils.isNotBlank(principal.getAttribute("name")) ? principal.getAttribute("name") : "UNKNOWN" );
        return Collections.singletonMap("name", name);
    }

}
