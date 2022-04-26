package com.example.pp_3_1_3;

import com.example.pp_3_1_3.model.Role;
import com.example.pp_3_1_3.model.User;
import com.example.pp_3_1_3.service.RoleService;
import com.example.pp_3_1_3.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitUsers {

    private final RoleService roleService;
    private final UserService userService;


    public InitUsers(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.addRole(roleAdmin);
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleUser);

        User user = new User("user@mail.ru", "user", 30, "user", "user");
        user.getRoleSet().add(roleUser);
        User admin = new User("admin@mail.ru", "admin",  35, "admin", "admin");
        admin.getRoleSet().add(roleAdmin);
        admin.getRoleSet().add(roleUser);
        userService.saveUser(admin);
        userService.saveUser(user);
    }
}
