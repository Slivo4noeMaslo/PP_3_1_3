package com.example.pp_3_1_3.controller;

import com.example.pp_3_1_3.model.Role;
import com.example.pp_3_1_3.service.RoleService;
import com.example.pp_3_1_3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUser(Model model, Principal principal) {

        StringBuilder roles = new StringBuilder();
        for (Role role : userService.getUserByUsername(principal.getName()).getRoleSet()) {
            roles.append(role.toString());
            roles.append(" ");
        }

        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", userService.getUserByUsername(principal.getName()));
        return "userPage";
    }
}
