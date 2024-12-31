package com.project.auth.model.response;

import com.project.auth.entity.RoleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Created by user on Jul, 2024
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse extends EmbeddedResponse {

    Integer id;
    String roleCode;
    String roleName;
    String description;
    boolean active;

    public RoleResponse(RoleEntity roleEntity) {
        super(roleEntity.getEmbeddedEntity());
        this.id = roleEntity.getId();
        this.roleCode = roleEntity.getRoleCode();
        this.roleName = roleEntity.getRoleName();
        this.description = roleEntity.getDescription();
        this.active = roleEntity.getEmbeddedEntity().isActive();
    }
}
