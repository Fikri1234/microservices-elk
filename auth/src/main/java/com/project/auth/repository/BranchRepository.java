package com.project.auth.repository;

import com.project.auth.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on Jul, 2024
 */

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

    List<BranchEntity> findAllByEmbeddedEntity_Active(boolean active);
}
