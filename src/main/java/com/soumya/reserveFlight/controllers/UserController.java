package com.soumya.reserveFlight.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.soumya.reserveFlight.entities.User;
import com.soumya.reserveFlight.repos.UserRepository;
import com.soumya.reserveFlight.services.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("/showReg")
	public String showRegisterationPage()
	  {
		return("login/registerUser");
	  }
	
	
	@RequestMapping(value = "/registerUser", method= RequestMethod.POST)
	public String register(@ModelAttribute("user")User user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return("login/login");
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage()
	  {
		return("login/login");
	  }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) 
	{
		
		//User user = userRepository.findByEmail(email);
//		if (user.getPassword().equals(password)) {
//			return "findFlights";
//		} else {
//			modelMap.addAttribute("msg", "Invalid user name or password .Please try again.");
//		}
		
		boolean loginResponse = securityService.login(email, password);
		if (loginResponse) {
			return "findFlights";
		} else {
			modelMap.addAttribute("msg", "Invalid user name or password .Please try again.");
		}
		return "login/login";
	}
}
