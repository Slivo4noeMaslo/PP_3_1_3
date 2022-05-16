package com.example.pp_3_1_3.service;

import com.example.pp_3_1_3.DAO.RoleDAO;
import com.example.pp_3_1_3.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    @Transactional
    public Role getRole(String name) {
        return roleDAO.findRoleByName(name);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roleDAO.findAll();
    }
}
