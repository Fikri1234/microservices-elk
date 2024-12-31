package com.project.auth.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Created by user on Jul, 2024
 */

@Data
@Entity
@Table(name = "m_role")
public class RoleEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7175608428005193310L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleCode;
    private String roleName;
    private String description;

    @Embedded
    private EmbeddedEntity embeddedEntity;
}
