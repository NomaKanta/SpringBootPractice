package com.example.demo.form;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignupForm implements Serializable {
	@NotBlank
	private String lastName;

	@NotBlank
	private String firstName;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

	private Timestamp currentSignInAt;

	private Timestamp updatedAt;

	private Timestamp createdAt;
}