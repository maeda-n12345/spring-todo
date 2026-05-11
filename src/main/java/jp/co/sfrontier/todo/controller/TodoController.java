package jp.co.sfrontier.todo.controller;

import java.security.Principal;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sfrontier.todo.form.TodoForm;
import jp.co.sfrontier.todo.service.TodoService;

/**
 * todo を表示する画面を制御するクラス
 */
@Controller
public class TodoController {

	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private TodoService todoService;

	/**
	 * Todo の一覧を表示する<br>
	 * <br>
	 * 
	 * @param model 画面へ渡す Model
	 * @return 遷移先画面
	 */
	@GetMapping("/todo/list")
	// TODO メソッド名を動詞にする
	public String list(Model model, Principal principal) {

		logger.info("Todo一覧画面表示");

		System.out.println(principal.getName());

		model.addAttribute("todoList", todoService.findAll());

		model.addAttribute("todoForm", new TodoForm());

		return "todo/list";
	}

	/**
	 * Todo を新規登録した画面を表示する<br>
	 * <br>
	 * 
	 * @param todoForm Todo入力フォーム
	 * @param bindingResult バリデーション結果
	 * @param model 画面へ渡す Model
	 * @return 遷移先画面
	 */
	@PostMapping("/todo/create")
	// TODO メソッド名を変更する
	public String create(@Valid TodoForm todoForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("todoList", todoService.findAll());

			return "todo/list";
		}

		todoService.create(todoForm);

		// 二重登録防止のため、リダイレクトする
		return "redirect:/todo/list";
	}

	/**
	 * ステータス更新後の画面を表示する<br>
	 * <br>
	 * 
	 * @param id  ステータスを更新する Todo の id
	 * @return ステータス更新後の画面
	 */
	@PostMapping("/todo/status/{id}")
	public String updateStatus(@PathVariable Long id) { // PathVariable でURLから値を取得する

		todoService.updateStatus(id);

		return "redirect:/todo/list";
	}

	/**
	 * Todo を削除したあとの画面を表示する<br>
	 * <br>
	 * 
	 * @param id 削除する Todo の id
	 * @return Todo を削除したあとの画面
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/todo/delete/{id}")
	public String delete(@PathVariable Long id) {

		todoService.delete(id);

		return "redirect:/todo/list";
	}
}
