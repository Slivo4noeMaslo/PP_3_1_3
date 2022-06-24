package com.example.pp_3_1_3.service;

import com.example.pp_3_1_3.model.Role;

import java.util.List;

public interface RoleService {

    Role getRole(Long id);

    void addRole(Role role);

    List<Role> getRoles();
}
