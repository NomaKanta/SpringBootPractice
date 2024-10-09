package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration // このクラスはSpringの設定クラスであることを示します
@EnableWebSecurity // Spring Securityを有効にする
public class SecurityConfig {

	@Autowired // PasswordEncoderのインスタンスを自動的に注入する
	private PasswordEncoder passwordEncoder;

	@Autowired // UserDetailsServiceImplのインスタンスを自動的に注入する
	private UserDetailsServiceImpl userDetailsService;

	@Autowired // グローバルな認証設定を行う
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean // SecurityFilterChainのBeanを定義
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/admin/signup", "/admin/signup/**", "/admin/signin").permitAll() // これらのURLへのアクセスを許可する
						.anyRequest().authenticated()) // その他のリクエストは認証を要求
				.formLogin(form -> form
						.loginPage("/admin/signin") // カスタムログインページのURLを指定
						.loginProcessingUrl("/admin/signin") // ログイン処理のURLを指定
						.usernameParameter("email") // ユーザー名のパラメータ名を指定
						.passwordParameter("password") // パスワードのパラメータ名を指定
						.defaultSuccessUrl("/admin/contacts", true) // ログイン成功時のリダイレクト先を指定
						.failureUrl("/admin/signin?error=true") // ログイン失敗時のリダイレクト先を指定
						.permitAll()) // ログインページへのアクセスを許可
				.logout(logout -> logout
						.logoutUrl("/admin/logout") // ログアウトのURLを指定
						.logoutSuccessUrl("/admin/signin") // ログアウト成功時のリダイレクト先を指定
						.invalidateHttpSession(true) // セッションを無効にする
						.deleteCookies("JSESSIONID") // クッキーを削除
						.permitAll()); // ログアウトURLへのアクセスを許可

		return http.build(); // SecurityFilterChainをビルドして返す
	}
}