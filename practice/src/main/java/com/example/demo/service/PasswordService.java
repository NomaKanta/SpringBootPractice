package com.example.demo.service;

public interface PasswordService {
	String encodePassword(String rawPassword);//パスワードをエンコードする

	boolean matches(String rawPassword, String encodedPassword);//パスワードとエンコードされたパスワードが一致するかを確認する
}