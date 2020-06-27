/*package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.security.user.name}")
	private String userName;
	
	@Value("${spring.security.user.password}")
	private String password;
	
	 * Use this class only if we need to use Encrypt Decrypt - Else basic Auth property in application.props is enough
	 * 
	 
	@Override
    protected void configure(HttpSecurity http) throws Exception{
         http
         .authorizeRequests()
	         .antMatchers("/encrypt").permitAll()
	         .antMatchers("/decrypt").permitAll()
	         .anyRequest().authenticated();
         
         http.csrf().disable().authorizeRequests()
         .antMatchers("/encrypt").permitAll()
         .antMatchers("/decrypt").permitAll()
         .anyRequest().authenticated().and()
         .httpBasic();
        
		http
        .csrf().disable()
        .antMatcher("/**")                
        .authorizeRequests()
        .antMatchers("/", "/encrypt**", "/decrypt**")
        .permitAll()
        .anyRequest().authenticated()
        .and()
		.formLogin()
			.permitAll();
		
		http
        //.csrf().disable()
        .authorizeRequests()
        .antMatchers("/**").authenticated()
        .antMatchers("/", "/encrypt**", "/decrypt**")
        .permitAll()
        
        ;
		
		http.csrf()
		.disable()
		.httpBasic();
    }

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username(userName)
				.password(password)
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
*/