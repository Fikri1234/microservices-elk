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
@Table(name = "m_param")
public class ParamEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5000536900957617824L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String paramName;
    private String paramValue;
    private String paramDescription;

    @Embedded
    private EmbeddedEntity embeddedEntity;
}
