package com.project.auth.service.impl;

import com.project.auth.entity.ParamEntity;
import com.project.auth.repository.ParamRepository;
import com.project.auth.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */

@Service
public class ParamServiceImpl implements ParamService {

    private ParamRepository paramRepository;

    @Autowired
    public ParamServiceImpl(ParamRepository paramRepository) {
        this.paramRepository = paramRepository;
    }

    @Override
    public Optional<ParamEntity> findById(Integer id) {
        return paramRepository.findById(id);
    }

    @Override
    public List<ParamEntity> findAll() {
        return paramRepository.findAllByEmbeddedEntity_Active(true);
    }

    @Override
    public Optional<ParamEntity> findByParamNameAndEmbeddedEntity_Active(String paramName, boolean active) {
        return paramRepository.findByParamNameAndEmbeddedEntity_ActiveAndParamValueIsNotNull(paramName, active);
    }

    @Override
    public ParamEntity save(ParamEntity entity) {
        return paramRepository.save(entity);
    }

    @Override
    public ParamEntity update(ParamEntity entity) {
        Optional<ParamEntity> optional = findById(entity.getId());
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<ParamEntity> optional = findById(id);
        if (optional.isPresent()) {
            optional.get().getEmbeddedEntity().setActive(false);
            save(optional.get());
        }
    }
}
