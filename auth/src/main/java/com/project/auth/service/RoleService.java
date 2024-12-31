package com.project.auth.service;

import com.project.auth.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */
public interface RoleService {

    Optional<RoleEntity> findById(Integer id);

    List<RoleEntity> findAll();

    RoleEntity save(RoleEntity entity);

    RoleEntity update(RoleEntity entity);

    void deleteById(Integer id);
}
