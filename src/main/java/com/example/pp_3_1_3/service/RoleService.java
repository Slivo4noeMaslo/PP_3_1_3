package com.example.pp_3_1_3.service;

import com.example.pp_3_1_3.DAO.RoleDAO;
import com.example.pp_3_1_3.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {

    Role getRole(String name);

    void addRole(Role role);

    List<Role> getRoles();
}
