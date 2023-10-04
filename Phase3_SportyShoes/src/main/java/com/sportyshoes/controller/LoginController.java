package com.sportyshoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
		
	@RequestMapping("/userlogin")
	public String userlogin() {
	
		return "UserLogin";
	}
	
	
	@RequestMapping("/forgotpwd")
	public String forgotpwd() {
	
		return "ForgotPassword";
	}
	
	@RequestMapping("/admindashboard")
	public String AdminDashboard()
	{
		return "AdminDashboard";
	}
	
	@RequestMapping(value = "/logins", method=RequestMethod.POST)
	public String AdminLogin(ModelMap model, @RequestParam String username, @RequestParam String password)
	{
		if(username.equals("Aishwarya") && password.equals("Aish123")) {
			
			return "redirect:/admindashboard";			
		}		
		else {
			return "AdminLogin";				
		}	
	}
	

}
