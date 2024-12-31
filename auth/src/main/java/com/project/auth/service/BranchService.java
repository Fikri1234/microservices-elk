package com.project.auth.service;

import com.project.auth.entity.BranchEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */
public interface BranchService {

    Optional<BranchEntity> findById(Integer id);

    List<BranchEntity> findAll();

    BranchEntity save(BranchEntity entity);

    BranchEntity update(BranchEntity entity);

    void deleteById(Integer id);
}
