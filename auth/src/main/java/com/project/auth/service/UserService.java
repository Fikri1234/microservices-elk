package com.project.auth.service;

import com.project.auth.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */
public interface UserService {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findFirstByUsernameOrEmail(String username, String email);

    Optional<UserEntity> findByResetToken(String resetToken);

    List<UserEntity> findAll();

    UserEntity save(UserEntity entity);

    UserEntity update(UserEntity entity);

    void deleteById(Long id);


}
