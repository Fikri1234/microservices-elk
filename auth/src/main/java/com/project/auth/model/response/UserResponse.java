package com.project.auth.model.response;

import com.project.auth.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse extends EmbeddedResponse {

    Long id;
    BranchResponse branch;
    String username;
    String email;
    String password;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;
    int failCounter;
    Instant lastLogin;
    String resetToken;
    Set<RoleResponse> authorities = new HashSet<>();

    public UserResponse(UserEntity userEntity) {
        super(userEntity.getEmbeddedEntity());
        this.id = userEntity.getId();
        this.branch = new BranchResponse(userEntity.getBranch());
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.accountNonExpired = userEntity.isAccountNonExpired();
        this.accountNonLocked = userEntity.isAccountNonLocked();
        this.credentialsNonExpired = userEntity.isCredentialsNonExpired();
        this.enabled = userEntity.getEmbeddedEntity().isActive();
        this.failCounter = userEntity.getFailCounter();
        this.lastLogin = userEntity.getLastLogin();
        this.resetToken = userEntity.getResetToken();
        this.authorities = userEntity.getAuthorities().stream().map(x -> new RoleResponse(x)).collect(Collectors.toSet());
    }
}
