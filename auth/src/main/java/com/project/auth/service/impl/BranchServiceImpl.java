package com.project.auth.service.impl;

import com.project.auth.entity.BranchEntity;
import com.project.auth.repository.BranchRepository;
import com.project.auth.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */

@Service
public class BranchServiceImpl implements BranchService {

    private BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Optional<BranchEntity> findById(Integer id) {
        return branchRepository.findById(id);
    }

    @Override
    public List<BranchEntity> findAll() {
        return branchRepository.findAllByEmbeddedEntity_Active(true);
    }

    @Override
    public BranchEntity save(BranchEntity entity) {
        return branchRepository.save(entity);
    }

    @Override
    public BranchEntity update(BranchEntity entity) {
        Optional<BranchEntity> optional = findById(entity.getId());
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<BranchEntity> optional = findById(id);
        if (optional.isPresent()) {
            optional.get().getEmbeddedEntity().setActive(false);
            save(optional.get());
        }
    }
}
