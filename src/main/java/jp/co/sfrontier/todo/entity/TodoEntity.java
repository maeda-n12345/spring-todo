package jp.co.sfrontier.todo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * todoテーブルに対応するエンティティクラス<br>
 * <br>
 * JPA によりテーブルとマッピングされる
 * 
 */
@Entity
@Table(name = "todo")
@Data
public class TodoEntity {

	@Id // 主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY) // DB が自動採番
	private Long id;

	@Column(name = "title", nullable = false, length = 100)
	private String title;

	@Column(name = "status", nullable = false)
	private Integer status;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
