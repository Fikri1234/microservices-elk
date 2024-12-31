package com.project.auth.controller;

import com.project.auth.entity.RoleEntity;
import com.project.auth.entity.UserEntity;
import com.project.auth.model.response.RoleResponse;
import com.project.auth.model.response.UserResponse;
import com.project.auth.service.RoleService;
import com.project.commons.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on Jul, 2024
 */

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllRole() {
        List<RoleResponse> responses = new ArrayList<>();
        try {
            List<RoleEntity> entities = roleService.findAll();
            responses = entities.stream().map(RoleResponse::new).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseApi(responses), HttpStatus.OK);
    }
}
