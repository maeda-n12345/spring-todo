package jp.co.sfrontier.todo.security;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.sfrontier.todo.entity.UserEntity;

/**
 * Spring Security 専用のユーザー情報を管理するクラス<br>
 * <br>
 * Spring Security は UserDitails の情報しか解釈しないため、UserEntity を Security 形式に変換する。
 */
public class LoginUserDetails implements UserDetails {

	private final UserEntity user;

	public LoginUserDetails(UserEntity user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(user.getRole()));
	}

	@Override
	public @Nullable String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
	}

}
