package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//Use this for redirecting to different pages based on the Role
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					//.antMatchers("/", "/home", "/hello").permitAll()
					.anyRequest().authenticated()
					.and()
				.formLogin()
					//.loginPage("/login")
				    // .loginPage()
					.permitAll()
					.successHandler(myAuthenticationSuccessHandler())
					.failureUrl("/error")
					.and()
				.logout()
					.permitAll();
		}

		@Bean
	    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	        return new MyAuthenticationSuccessHandler();
	    }
	
	  @Override
	  public void configure(AuthenticationManagerBuilder builder)
	          throws Exception {
	      builder.userDetailsService(new MyUserDetailsService());
	  }
	
}
