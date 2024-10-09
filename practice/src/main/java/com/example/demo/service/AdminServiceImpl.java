package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordService passwordService;

	@Override
	public void saveAdmin(SignupForm signupForm) {
		Admin admin = new Admin();

		admin.setLastName(signupForm.getLastName());
		admin.setFirstName(signupForm.getFirstName());
		admin.setEmail(signupForm.getEmail());
		admin.setPassword(passwordService.encodePassword(signupForm.getPassword()));
		admin.setCurrentSignInAt(signupForm.getCurrentSignInAt());
		admin.setUpdatedAt(signupForm.getUpdatedAt());
		admin.setCreatedAt(signupForm.getCreatedAt());

		adminRepository.save(admin);
	}
}