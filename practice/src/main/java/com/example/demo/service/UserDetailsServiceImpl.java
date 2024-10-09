package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AdminRepository adminRepository; // AdminRepositoryのインスタンスを保持する

	// コンストラクタでAdminRepositoryを注入
	public UserDetailsServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// メールアドレスでAdminを検索し、見つからなければ例外をスローする
		Admin admin = adminRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("メールアドレスに該当するユーザーが見つかりません: " + email));

		// UserDetailsオブジェクトを作成して返す
		return org.springframework.security.core.userdetails.User.builder()
				.username(admin.getEmail()) // ユーザー名としてメールアドレスを設定
				.password(admin.getPassword()) // パスワードを設定
				.roles("ADMIN") // ロールを設定
				.build();
	}
}