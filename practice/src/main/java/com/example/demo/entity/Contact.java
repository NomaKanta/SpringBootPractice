package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity //Entityクラスであることを示すために使用されます。
@Data
@Table(name = "contacts")
public class Contact {
	@Id //@Idアノテーションはエンティティのプライマリキーを指定
	@GeneratedValue(strategy = GenerationType.AUTO) //@GeneratedValueアノテーションはそのプライマリキーの値の生成方法を指定します。
	@Column(name = "id") //@Columnアノテーションは、エンティティのフィールドに対して、データベーステーブルの列に関連する属性を指定するために使用されます。
	private Long id;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "zip_code", nullable = false)
	private String zipCode;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "building_name", nullable = false)
	private String buildingName;

	@Column(name = "contact_type", nullable = false)
	private String contactType;

	@Column(name = "body", nullable = false)
	private String body;
}