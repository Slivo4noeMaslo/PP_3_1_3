package com.example.pp_3_1_3.DAO;

import com.example.pp_3_1_3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
