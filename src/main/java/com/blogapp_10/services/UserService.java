package com.blogapp_10.services;

import com.blogapp_10.dto.RegisterDto;
import com.blogapp_10.entity.User;

public interface UserService {
	
	void saveUser(RegisterDto registerDto);
	
	User findByEmail(String email);

}
