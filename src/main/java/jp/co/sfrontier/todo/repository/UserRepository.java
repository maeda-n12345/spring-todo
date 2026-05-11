package jp.co.sfrontier.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sfrontier.todo.entity.UserEntity;

/**
 * users のテーブル操作を行うリポジトリ<br>
 * <br>
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);
}
