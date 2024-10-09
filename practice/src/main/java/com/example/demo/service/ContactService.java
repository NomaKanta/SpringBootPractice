package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.form.ContactUpdateForm;

public interface ContactService {

	void saveContact(ContactForm contactForm); // ContactFormのデータを保存する

	List<Contact> getAllContacts(); // すべてのContactオブジェクトを取得する

	Optional<Contact> getContactById(Long id); // 指定されたIDのContactオブジェクトを取得する

	void updateContact(ContactUpdateForm contactUpdateForm); // ContactUpdateFormのデータで既存のContactを更新する

	void deleteContactById(Long id); // 指定されたIDのContactオブジェクトを削除する
}