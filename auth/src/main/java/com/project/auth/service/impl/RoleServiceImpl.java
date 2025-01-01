package com.project.auth.service.impl;

import com.project.auth.entity.RoleEntity;
import com.project.auth.repository.RoleRepository;
import com.project.auth.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Optional<RoleEntity> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAllByEmbeddedEntity_Active(true);
    }

    @Override
    public RoleEntity save(RoleEntity entity) {
        return roleRepository.save(entity);
    }

    @Override
    public RoleEntity update(RoleEntity entity) {
        Optional<RoleEntity> optional = findById(entity.getId());
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<RoleEntity> optional = findById(id);
        if (optional.isPresent()) {
            optional.get().getEmbeddedEntity().setActive(false);
            save(optional.get());
        }
    }
}
