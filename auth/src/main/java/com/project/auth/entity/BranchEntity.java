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

@Data
@Entity
@Table(name="m_branch")
public class BranchEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3339403326416642851L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String owner;
    private String address;
    private String npwp;
    private String logo;
    private Integer taxStatus;
    private Integer onlineStatus;
    @Embedded
    private EmbeddedEntity embeddedEntity;
}
