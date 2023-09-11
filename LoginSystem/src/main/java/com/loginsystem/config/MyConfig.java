package com.loginsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.loginsystem.security.JWTAuthenticationEntryPoint;
import com.loginsystem.security.JWTAuthentictionFilter;


@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Autowired
    private JWTAuthenticationEntryPoint point;
    @Autowired
    private JWTAuthentictionFilter filter;
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new UserDetailsServicesImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager manager(AuthenticationConfiguration builder) throws Exception {
		
		return builder.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(this.passwordEncoder());
		authenticationProvider.setUserDetailsService(this.getDetailsService());
		return authenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain getCongiure(HttpSecurity http) throws Exception {
		
		http .csrf().disable()
			 .authorizeHttpRequests()
			 .requestMatchers("/user/**").hasRole("USER")
			 .requestMatchers("/**")
			 .permitAll()
			 .anyRequest().authenticated().and()
			 .exceptionHandling(ex->ex.authenticationEntryPoint(point))
			 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //we not store data on server
			 .formLogin()
			 .loginPage("/signin")
			 .defaultSuccessUrl("/user/dashboard")
			 .loginProcessingUrl("/signin-process");
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(this.authenticationProvider());
		return http.build();
	}
	
	
	
	
	
	
	
}
