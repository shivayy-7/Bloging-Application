package com.blogapp_10.serviceImpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapp_10.dto.RegisterDto;
import com.blogapp_10.entity.Role;
import com.blogapp_10.entity.User;
import com.blogapp_10.repositories.RoleRepository;
import com.blogapp_10.repositories.UserRepository;
import com.blogapp_10.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepo;
	
	private RoleRepository roleRepo;
	
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepo,
			               RoleRepository roleRepo,
			               PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}
	

	@Override
	public void saveUser(RegisterDto registerDto) {
		User user = new User();
		user.setName(registerDto.getFirstName() + " " + registerDto.getLastName());
		user.setEmail(registerDto.getEmail());
		// use spring security to encrypt the password
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//		user.setPassword(registerDto.getPassword());
		Role role = roleRepo.findByName("ROLE_GUEST");
		// we can use this also
//		List<Role> roles = new ArrayList<>();
//		roles.add(role);
//		user.setRoles(roles);
		user.setRoles(Arrays.asList(role));
        userRepo.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

}
