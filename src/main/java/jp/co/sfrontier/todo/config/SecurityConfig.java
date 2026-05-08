package jp.co.sfrontier.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security の設定クラス<br>
 * <br>
 * form認証を行い、ログインしていないユーザーのアクセスを制御する。
 */
@Configuration
public class SecurityConfig {

	/**
	 * Spring Security の認可設定を行う
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// URLごとのアクセス制御
		http.authorizeHttpRequests(auth -> auth

				// ログイン画面は誰でもアクセス可能
				.requestMatchers("/login")
				.permitAll()

				// ログイン画面以外はログイン必須
				.anyRequest()
				.authenticated())

				// form 認証
				.formLogin(form -> form

						.loginPage("/login")
						.defaultSuccessUrl("/todo/list", true)// ログイン成功後に画面遷移
						.permitAll())

				.logout(logout -> logout
						.logoutSuccessUrl("/login"));
		// TODO ログインに失敗したときの処理

		return http.build();
	}

	/**
	 * メモリ上にログインユーザー情報を保持する<br>
	 * <br>
	 * 学習用に固定ユーザーを定義する
	 * 
	 * @param passwordEncoder パスワードエンコーダ
	 * @return InMemoryUserDetailsManager
	 */
	// userDetailsService オブジェクトが ID・パスワードを保持している
	@Bean
	InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {

		UserDetails user = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("password")) // パスワードをハッシュ化する
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	
	/**
	 * パスワードエンコーダを生成する<br>
	 * <br>
	 * BCrypt によりパスワードをハッシュ化する
	 * 
	 * @return PasswordEncoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
