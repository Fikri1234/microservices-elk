package com.project.auth.service;

import com.project.auth.entity.ParamEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */
public interface ParamService {

    Optional<ParamEntity> findById(Integer id);

    List<ParamEntity> findAll();

    Optional<ParamEntity> findByParamNameAndEmbeddedEntity_Active(String paramName, boolean active);

    ParamEntity save(ParamEntity entity);

    ParamEntity update(ParamEntity entity);

    void deleteById(Integer id);
}
