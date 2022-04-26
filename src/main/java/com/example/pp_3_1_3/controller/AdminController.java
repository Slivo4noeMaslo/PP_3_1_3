package com.example.pp_3_1_3.controller;


import com.example.pp_3_1_3.model.User;
import com.example.pp_3_1_3.service.RoleService;
import com.example.pp_3_1_3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("thisUser", userService.getUserByUsername(principal.getName()));

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "adminPage";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") User user,  Model model) {
        return "redirect:/admin";
    }
}
