package com.example.demo.form;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SigninForm implements Serializable {
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

	private Timestamp currentSignInAt;

	private Timestamp updatedAt;
}