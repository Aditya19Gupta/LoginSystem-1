package com.loginsystem.controller;


import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loginsystem.config.UserCustomDetails;
import com.loginsystem.config.UserDetailsServicesImpl;
import com.loginsystem.entities.JWTRequest;
import com.loginsystem.entities.JWTResponse;
import com.loginsystem.entities.User;
import com.loginsystem.repository.UserRepo;
import com.loginsystem.security.JWTHelper;

@Controller
@RequestMapping("/user")
public class LogController {
	@Autowired
	private UserRepo repo;
	@Autowired
    private UserDetailsServicesImpl userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JWTHelper helper;

    private Logger logger = LoggerFactory.getLogger(LogController.class);

	
	@GetMapping("/dashboard")
	public String getDashboard(Model m,Principal p,@RequestBody JWTRequest request) {
		
		this.doAuthenticate(request.getEmail(), request.getPassword());


        UserCustomDetails userDetails = (UserCustomDetails) userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
		
        
        
		User user = this.repo.findUserByEmail(p.getName());
		
		m.addAttribute("user",user.getName());
		m.addAttribute("title","LoginSystem-Dashboard");
		
		return "dashboard";
	}
	private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


}
