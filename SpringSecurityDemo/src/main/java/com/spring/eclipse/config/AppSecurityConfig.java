package com.spring.eclipse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class AppSecurityConfig{
	
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.cors().disable();
		 http.csrf().disable();
		 
        return http.build();
    }
	
//	    @Bean
//	    public InMemoryUserDetailsManager userDetailsService() {
//	        UserDetails user = User.withDefaultPasswordEncoder()
//	            .username("user")
//	            .password("password")
//	            .roles("USER")
//	            .build();
//	        return new InMemoryUserDetailsManager(user);
//	    }
	

}
