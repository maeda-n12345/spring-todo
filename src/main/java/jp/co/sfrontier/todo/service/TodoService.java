package jp.co.sfrontier.todo.service;

import java.util.List;

import jp.co.sfrontier.todo.entity.TodoEntity;
import jp.co.sfrontier.todo.form.TodoForm;

/**
 * Todo の業務ロジックを定義する Service インタフェース
 */
public interface TodoService {

	/**
	 * Todo 一覧を取得する
	 * @return Todo の一覧
	 */
	List<TodoEntity> findAll();

	/**
	 * Todo を新規登録する
	 * @param todoForm Todo入力フォーム
	 */
	void create(TodoForm todoForm);

	/**
	 * Todo のステータスを更新する
	 * @param id ステータスを更新する Todo の id
	 */
	void updateStatus(Long id);

	/**
	 * Todo を削除する
	 * @param id 削除する Todo の id
	 */
	void delete(Long id);
}
