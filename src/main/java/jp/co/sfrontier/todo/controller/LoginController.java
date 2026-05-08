package jp.co.sfrontier.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログイン画面を制御するクラス<br>
 * <br>
 */
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
}
