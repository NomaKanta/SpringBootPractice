package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactUpdateForm;
import com.example.demo.form.SigninForm;
import com.example.demo.form.SignupForm;
import com.example.demo.service.AdminService;
import com.example.demo.service.ContactService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ContactService contactService;
	
	//管理者の新規登録画面
	@GetMapping("/admin/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "admin/signup"; 
	}

	@PostMapping("/admin/signup")
	public String signup(@Validated @ModelAttribute("signupForm") SignupForm signupForm, BindingResult errorResult,
			HttpServletRequest request) {
		if (errorResult.hasErrors()) {
			return "admin/signup"; //エラーがある場合、サインアップページに戻る
		}

		HttpSession session = request.getSession();
		session.setAttribute("signupForm", signupForm); // サインアップフォームをセッションに保存
		return "redirect:/admin/signup/confirm"; // 確認ページにリダイレクト
	}

	@GetMapping("/admin/signup/confirm")
	public String confirm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm"); // セッションからサインアップフォームを取得
		model.addAttribute("signupForm", signupForm); // サインアップフォームをモデルに追加
		return "admin/signupConfirmation"; // サインアップ確認ページを表示
	}

	@PostMapping("/admin/signup/register")
	public String register(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm"); // セッションからサインアップフォームを取得
		adminService.saveAdmin(signupForm); // 管理者情報を保存
		return "redirect:/admin/signup/complete"; // 完了ページにリダイレクト
	}

	@GetMapping("/admin/signup/complete")
	public String complete(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm"); // セッションからサインアップフォームを取得
		model.addAttribute("signupForm", signupForm); // サインアップフォームをモデルに追加
		session.invalidate(); // セッションを無効化してデータをクリア
		return "admin/signupCompletion"; // サインアップ完了ページを表示
	}
	//管理者のログイン画面
	@GetMapping("/admin/signin")
	public String signin(Model model, HttpServletRequest request) {
		String error = request.getParameter("error");
		if ("true".equals(error)) {
			model.addAttribute("error", "メールアドレスまたはパスワードが正しくありません"); // ログイン失敗時のエラーメッセージを追加
		}
		model.addAttribute("signinForm", new SigninForm()); // 新しいサインインフォームをモデルに追加
		return "admin/signin"; // サインインページを表示
	}
	//お問い合わせの一覧画面(ログイン後のTOP画面)
	@GetMapping("/admin/contacts")
	public String contact(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SigninForm signinForm = (SigninForm) session.getAttribute("signinForm"); // セッションからサインインフォームを取得
		model.addAttribute("signinForm", signinForm); // サインインフォームをモデルに追加
		model.addAttribute("inquiries", contactService.getAllContacts()); // すべての問い合わせをモデルに追加
		return "admin/contacts"; // 問い合わせページを表示
	}
	//お問い合わせの詳細画面
	@GetMapping("/admin/contacts/:{id}")
	public String showContactDetails(@PathVariable Long id, Model model) {
		Contact contact = contactService.getContactById(id).orElse(null); // IDで問い合わせを取得
		if (contact != null) {
			model.addAttribute("contact", contact); // 問い合わせが見つかった場合、モデルに追加
			return "admin/contactDetail"; // 問い合わせ詳細ページを表示
		} else {
			model.addAttribute("errorMessage", "お問い合わせが見つかりません"); // 問い合わせが見つからない場合のエラーメッセージを追加
			return "admin/error"; // エラーページを表示
		}
	}
	//お問い合わせの編集画面
	@GetMapping("/admin/contacts/:{id}/edit")
	public String showEditContact(@PathVariable Long id, Model model) {
		Contact contact = contactService.getContactById(id).orElse(null); // IDで問い合わせを取得
		if (contact != null) {
			ContactUpdateForm contactUpdateForm = new ContactUpdateForm();
			BeanUtils.copyProperties(contact, contactUpdateForm); // contactからcontactUpdateFormにプロパティをコピー
			model.addAttribute("contact", contact); // 問い合わせをモデルに追加
			model.addAttribute("contactUpdateForm", contactUpdateForm); // 問い合わせ更新フォームをモデルに追加
			return "admin/contactEdit"; // 問い合わせ編集ページを表示
		} else {
			model.addAttribute("errorMessage", "お問い合わせが見つかりません"); // 問い合わせが見つからない場合のエラーメッセージを追加
			return "admin/error"; // エラーページを表示
		}
	}
	//更新
	@PostMapping("/admin/contacts/update")
	public String updateContact(@Validated @ModelAttribute("contactUpdateForm") ContactUpdateForm contactUpdateForm,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/contactEdit"; // エラーがある場合、問い合わせ編集ページに戻る
		}
		contactService.updateContact(contactUpdateForm); // 問い合わせを更新
		return "redirect:/admin/contacts"; // 問い合わせページにリダイレクト
	}
	//削除
	@PostMapping("/admin/contacts/:{id}/delete")
	public String deleteContact(@PathVariable Long id) {
		contactService.deleteContactById(id); // IDで問い合わせを削除
		return "redirect:/admin/contacts"; // 問い合わせページにリダイレクト
	}
}