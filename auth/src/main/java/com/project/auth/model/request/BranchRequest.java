package com.project.auth.model.request;

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
public class BranchRequest {

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
}
