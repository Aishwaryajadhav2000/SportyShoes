package com.sportyshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;	
	@RequestMapping("/home")
	public String home()
	{
		System.out.println("This is my Home");
		return "Home";
	}
	
	@RequestMapping("/logins")
	public String login()
	{
		return "AdminLogin";
	}
	
	@RequestMapping("/register")
	public String register()
	{return "RegisterUser";}
	
	@GetMapping("/home")
	public String getProducts(Model model)
	{	
    model.addAttribute("products", productService.getAllProduct());
		return "Home";
	}
	
	
	@GetMapping("/home/product/cart/{id}")
	public String addtocart(@PathVariable long id, Model model)
	{
		return "Cart";
	}
			
			
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
