package jp.co.sfrontier.todo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * ユーザー登録の入力を受け取る Form クラス
 */
@Data
public class UserForm {

	@NotBlank(message = "ユーザー名を入力してください")
	@Size(max = 50)
	private String username;

	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 4, max = 100)
	private String password;
}
