package com.project.auth.repository;

import com.project.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findFirstByUsernameOrEmailAndEmbeddedEntity_Active(String username, String email, boolean active);

    Optional<UserEntity> findByResetToken(String resetToken);

    List<UserEntity> findAllByEmbeddedEntity_Active(boolean active);
}
