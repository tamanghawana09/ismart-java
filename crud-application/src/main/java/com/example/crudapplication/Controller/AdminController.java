package com.example.crudapplication.Controller;

import com.example.crudapplication.Repository.AdminRepository;
import com.example.crudapplication.Entities.Admin;
import com.example.crudapplication.Entities.Users;
import com.example.crudapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    //Admin Controller Logic
    @GetMapping("/login")
    public String adminLogin(){ return "admin-login"; }

    @GetMapping("/register")
    public String adminRegister(){ return "admin-register"; }

    @GetMapping("/dashboard")
    public String dashboardAdmin(Model model, Principal principal){
        model.addAttribute("username",principal.getName());
        return "dashboard-admin";
    }

    @PostMapping("/save")
    public String saveAdmin(@RequestParam String username, @RequestParam String password,
                            @RequestParam String email){
        Admin admin = new Admin(
                username,
                passwordEncoder.encode(password),
                email,
                "ADMIN"
        );
        adminRepository.save(admin);
        return "redirect:/admin/login";

    }

    //user details controller
    @GetMapping("/list-user")
    public String listUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "/list-user";
    }

    @GetMapping("/new-user")
    public String showUserAddForm(Model model){
        model.addAttribute("users", new Users());
        return "/new-user";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute Users user){
        userService.saveUser(user);
        return "redirect:/admin/list-user";
    }
    @GetMapping("/edit-user/{id}")
    public String showUserEditForm(@PathVariable Long id, Model model) {
        Users user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "edit-user";
    }
    // Update user
    @PostMapping("/update-user/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute Users user) {
        userService.updateUser(id, user);
        return "redirect:/admin/list-user";
    }

    // Delete user
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/list-user";
    }


}
