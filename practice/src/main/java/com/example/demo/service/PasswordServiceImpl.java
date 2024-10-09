package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

	private final PasswordEncoder passwordEncoder;
	
	// コンストラクタでPasswordEncoderを注入
	public PasswordServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);// パスワードをエンコードして返します
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);// パスワードとエンコードされたパスワードが一致するかを確認します
	}
}