package com.blogapp_10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blogapp_10.dto.RegisterDto;
import com.blogapp_10.entity.User;
import com.blogapp_10.services.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	// handler method to handle login page request
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	// handler method to handle user registration request
	@GetMapping("/register")
	private String showRegistrationForm(Model model) {
		// this object holds form data
		RegisterDto user = new RegisterDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	// handler method to handle user registration form submit request
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") RegisterDto user,
			                BindingResult result,
			                Model model) {
		User existingUser = userService.findByEmail(user.getEmail());
		if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "There is already a user with same email");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		userService.saveUser(user);
		return "redirect:/register?success";
	}
	
	

}
