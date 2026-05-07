package jp.co.sfrontier.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sfrontier.todo.entity.TodoEntity;

/**
 * todo のテーブル操作を行うリポジトリ<br>
 * <br>
 * JpaRepository を継承し、Entity と主キーの型をジェネリクスで指定する
 * 
 */
public interface TodoRepository extends JpaRepository<TodoEntity, Long>{
	
}
