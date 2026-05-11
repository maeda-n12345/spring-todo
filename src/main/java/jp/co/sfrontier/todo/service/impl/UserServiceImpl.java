/**
 * 
 */
package jp.co.sfrontier.todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.todo.entity.UserEntity;
import jp.co.sfrontier.todo.form.UserForm;
import jp.co.sfrontier.todo.repository.UserRepository;
import jp.co.sfrontier.todo.service.UserService;

/**
 * ユーザー登録の業務ロジックを提供するクラス
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncorder;

	@Override
	public void create(UserForm userForm) {

		UserEntity user = new UserEntity();

		user.setUsername(userForm.getUsername());

		user.setPassword(passwordEncorder.encode(userForm.getPassword()));

		user.setRole("ROLE_USER");

		userRepository.save(user);
	}

}
