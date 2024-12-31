package com.project.auth.service.impl;

import com.project.auth.entity.UserEntity;
import com.project.auth.repository.UserRepository;
import com.project.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findFirstByUsernameOrEmail(String username, String email) {
        return userRepository.findFirstByUsernameOrEmailAndEmbeddedEntity_Active(username, email, true);
    }

    @Override
    public Optional<UserEntity> findByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAllByEmbeddedEntity_Active(true);
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        Optional<UserEntity> optional = findById(entity.getId());
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Optional<UserEntity> optional = findById(id);
        if (optional.isPresent()) {
            optional.get().getEmbeddedEntity().setActive(false);
            save(optional.get());
        }
    }
}
