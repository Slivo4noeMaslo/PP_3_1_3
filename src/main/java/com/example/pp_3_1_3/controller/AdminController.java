package com.example.pp_3_1_3.controller;


import com.example.pp_3_1_3.model.Role;
import com.example.pp_3_1_3.model.User;
import com.example.pp_3_1_3.service.RoleService;
import com.example.pp_3_1_3.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String adminPage(Model model, Authentication auth) {
        StringBuilder roles = new StringBuilder();
        User thisUser = (User) auth.getPrincipal();
        for (Role role : thisUser.getRoles()) {
            roles.append(role.toString());
            roles.append(" ");
        }

        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", thisUser);

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "adminPage";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("editUser") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
