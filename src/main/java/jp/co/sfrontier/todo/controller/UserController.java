package jp.co.sfrontier.todo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sfrontier.todo.form.UserForm;
import jp.co.sfrontier.todo.service.UserService;

/**
 * ユーザー登録画面を制御するクラス<br>
 * <br>
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String signup(Model model) {

		model.addAttribute("userForm", new UserForm());

		return "signup";
	}

	@PostMapping("/signup")
	public String create(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			return "signup";
		}

		userService.create(userForm);

		return "redirect:/login";

	}
}
