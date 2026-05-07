package jp.co.sfrontier.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//コンフィグレーション、オートコンフィグレーション、コンポーネントスキャンを行うアノテーション
@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

}
