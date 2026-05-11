package jp.co.sfrontier.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.todo.entity.UserEntity;
import jp.co.sfrontier.todo.repository.UserRepository;

/**
 * ログイン時にDBからユーザ情報を取得するクラス
 */
@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("ユーザーが存在しません"));
		
		return new LoginUserDetails(user);
	}

}
