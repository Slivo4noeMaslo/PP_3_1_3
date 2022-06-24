package com.example.pp_3_1_3.controller;

import com.example.pp_3_1_3.model.Role;
import com.example.pp_3_1_3.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String getUser(Model model, Authentication auth) {

        StringBuilder roles = new StringBuilder();
        User user = (User) auth.getPrincipal();
        for (Role role : user.getRoles()) {
            roles.append(role.toString());
            roles.append(" ");
        }

        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", user);
        return "userPage";
    }
}
