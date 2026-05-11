package jp.co.sfrontier.todo.service;

import jp.co.sfrontier.todo.form.UserForm;

/**
 * ユーザー登録の業務ロジックを提供するサービスインタフェース<br>
 * <br>
 */
public interface UserService {

	void create(UserForm userForm);
}
