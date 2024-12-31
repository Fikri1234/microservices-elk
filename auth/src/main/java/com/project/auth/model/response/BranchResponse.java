package com.project.auth.model.response;

import com.project.auth.entity.BranchEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchResponse extends EmbeddedResponse {

    Integer id;
    String code;
    String name;
    String owner;
    String address;
    String npwp;
    String logo;
    Integer taxStatus;
    Integer onlineStatus;
    boolean active;

    public BranchResponse(BranchEntity branchEntity) {
        super(branchEntity.getEmbeddedEntity());
        this.id = branchEntity.getId();
        this.code = branchEntity.getCode();
        this.name = branchEntity.getName();
        this.owner = branchEntity.getOwner();
        this.address = branchEntity.getAddress();
        this.npwp = branchEntity.getNpwp();
        this.logo = branchEntity.getLogo();
        this.taxStatus = branchEntity.getTaxStatus();
        this.onlineStatus = branchEntity.getOnlineStatus();
        this.active = branchEntity.getEmbeddedEntity().isActive();
    }
}
