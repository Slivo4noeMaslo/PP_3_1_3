package com.example.pp_3_1_3.service;

import com.example.pp_3_1_3.DAO.RoleDAO;
import com.example.pp_3_1_3.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    public Role getRole(String name) {
        return roleDAO.findRoleByName(name);
    }

    @Override
    public void addRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.findAll();
    }
}
