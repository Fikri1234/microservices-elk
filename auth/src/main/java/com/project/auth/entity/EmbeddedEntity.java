package com.project.auth.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Embeddable
@Data
public class EmbeddedEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3646603889181096363L;

    private boolean active;

    @CreatedDate
    private Instant createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private Instant lastUpdated;

    @LastModifiedBy
    private String lastUpdatedBy;
}
