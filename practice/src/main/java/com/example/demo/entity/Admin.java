package com.example.demo.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "admins")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "current_sign_in_at")
	private Timestamp currentSignInAt;

	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@PrePersist
	protected void onCreate() {
		Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
		createdAt = currentTime;
		updatedAt = currentTime;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = Timestamp.valueOf(LocalDateTime.now());
	}

	public void setPassword(String password) {
		this.password = password;
	}
}