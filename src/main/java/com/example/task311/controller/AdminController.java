package com.example.task311.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.task311.model.User;
import com.example.task311.service.RoleService;
import com.example.task311.service.UserService;

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
    @GetMapping("/list_users")
    public String viewUsersList(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.findAllUsers());
        model.addAttribute("allRoles", roleService.findAllRoles());
        model.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        return "users";
    }
    @GetMapping("/new")
    public String addUserPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "new_user";
    }
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roles ){
        roleService.setRolesToUser(user, roles);
        userService.addUser(user);
        return "redirect:/admin/list_users";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model, Principal principal) {
        model.addAttribute("allRoles", roleService.findAllRoles());
        model.addAttribute("userRoles", roleService.getRoleById(id));
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        return "/user_form";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roles) {
        roleService.setRolesToUser(user, roles);
        userService.updateUser(user);
        return "redirect:/admin/list_users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin/list_users";
    }
}
