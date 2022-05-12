package com.example.pp_3_1_3.controller;


import com.example.pp_3_1_3.model.Role;
import com.example.pp_3_1_3.model.User;
import com.example.pp_3_1_3.service.RoleService;
import com.example.pp_3_1_3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        StringBuilder roles = new StringBuilder();
        for (Role role : userService.getUserByUsername(principal.getName()).getRoleSet()) {
            roles.append(role.toString());
            roles.append(" ");
        }

        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", userService.getUserByUsername(principal.getName()));

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "adminPage";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") User user,
                             @ModelAttribute("roleSet") String[] roles) {
        for (String role : roles) {
            user.getRoleSet().add(roleService.getRole(role));
        }

        System.out.println(user.getId() + '\n' + user.getFirstName() + '\n'
                + user.getLastName() + '\n' + user.getAge() + '\n' + user.getEmail()
                + '\n' + user.getPassword() + '\n' + user.getRoleSet());
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("editUser") User user,
                           @ModelAttribute("roleSet") String[] roles) {
        for (String role : roles) {
            user.getRoleSet().add(roleService.getRole(role));
        }
        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getAge());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getRoleSet());
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
