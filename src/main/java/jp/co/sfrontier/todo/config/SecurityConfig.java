package jp.co.sfrontier.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security の設定クラス<br>
 * <br>
 * form認証を行い、ログインしていないユーザーのアクセスを制御する。
 */
@Configuration
@EnableMethodSecurity // 権限に応じてメソッドの実行を制限する
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
				.requestMatchers("/login", "/signup")
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
						.logoutSuccessUrl("/login?logout"));

		return http.build();
	}

	/**
	 * パスワードエンコーダを生成する<br>
	 * <br>
	 * BCrypt によりパスワードをハッシュ化する
	 * 
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
