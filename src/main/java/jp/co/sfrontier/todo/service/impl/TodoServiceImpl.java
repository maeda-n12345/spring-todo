package jp.co.sfrontier.todo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.todo.entity.TodoEntity;
import jp.co.sfrontier.todo.enums.TodoStatusEnum;
import jp.co.sfrontier.todo.form.TodoForm;
import jp.co.sfrontier.todo.repository.TodoRepository;
import jp.co.sfrontier.todo.service.TodoService;

/**
 * Todo の業務ロジックを提供するクラス<br>
 * <br>
 */
@Service
public class TodoServiceImpl implements TodoService {

	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

	@Autowired
	private TodoRepository todoRepository;

	/**
	 * Todo の一覧を取得する
	 */
	@Override
	public List<TodoEntity> findAll() {

		return todoRepository.findAll();
	}

	/**
	 * フォームに入力された情報を DB に登録する
	 */
	@Override
	public void create(TodoForm todoForm) {
		TodoEntity todo = new TodoEntity();

		// 入力された情報をセットする
		todo.setTitle(todoForm.getTitle());
		todo.setStatus(TodoStatusEnum.INCOMPLETE.getValue());
		todo.setCreatedAt(LocalDateTime.now());
		todo.setUpdatedAt(LocalDateTime.now());

		todoRepository.save(todo);

		logger.info("Todo登録 title={}", todo.getTitle());
	}

	/**
	 * Todo のステータスを更新する
	 */
	@Override
	public void updateStatus(Long id) {

		Optional<TodoEntity> optionalTodo = todoRepository.findById(id);

		if (optionalTodo.isEmpty()) {

			logger.warn("Todoが存在しません id={}", id);
			return;
		}

		TodoEntity todo = optionalTodo.get();

		if (todo.getStatus().equals(TodoStatusEnum.INCOMPLETE.getValue())) {
			todo.setStatus(TodoStatusEnum.COMPLETE.getValue());
		} else {
			todo.setStatus(TodoStatusEnum.INCOMPLETE.getValue());
		}

		todo.setUpdatedAt(LocalDateTime.now());

		todoRepository.save(todo);

		logger.info("Todo状態変更 id={}", id);
	}

	/**
	 * Todo を削除する
	 */
	@Override
	public void delete(Long id) {
		todoRepository.deleteById(id);

		logger.info("Todoを削除しました。id={} ", id);
	}

}
