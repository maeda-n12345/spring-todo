package jp.co.sfrontier.todo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * 画面入力を受け取るFormクラス<br>
 * <br>
 */
@Data
public class TodoForm {
	@NotBlank(message="Todoを入力してください")
	@Size(max=100,message="100文字以内で入力してください")
	private String title;
}
