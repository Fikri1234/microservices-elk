package com.project.auth.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.auth.entity.EmbeddedEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class EmbeddedResponse {

    boolean active;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    LocalDateTime createdDate;
    String createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    LocalDateTime lastUpdated;
    String lastUpdatedBy;

    protected EmbeddedResponse(EmbeddedEntity embeddedEntity) {
        this.active = embeddedEntity.isActive();
        this.createdDate = embeddedEntity.getCreatedDate() == null ? null : LocalDateTime.ofInstant(embeddedEntity.getCreatedDate(), ZoneOffset.UTC);
        this.createdBy = embeddedEntity.getCreatedBy();
        this.lastUpdated = embeddedEntity.getLastUpdated() == null ? null : LocalDateTime.ofInstant(embeddedEntity.getLastUpdated(), ZoneOffset.UTC);
        this.lastUpdatedBy = embeddedEntity.getLastUpdatedBy();
    }
}
