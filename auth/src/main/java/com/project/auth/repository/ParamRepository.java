package com.project.auth.repository;

import com.project.auth.entity.ParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */

@Repository
public interface ParamRepository extends JpaRepository<ParamEntity, Integer> {

    List<ParamEntity> findAllByEmbeddedEntity_Active(boolean active);

    Optional<ParamEntity> findByParamNameAndEmbeddedEntity_ActiveAndParamValueIsNotNull(String paramName, boolean active);
}
