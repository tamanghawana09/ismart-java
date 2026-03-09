package com.example.crudapplication.Controller;

import com.example.crudapplication.Entities.Students;
import com.example.crudapplication.Entities.Users;
import com.example.crudapplication.Service.CustomerUserDetailsService;
import com.example.crudapplication.Service.StudentsService;
import com.example.crudapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;


    @GetMapping("/")
    public String index(){ return "index"; }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String registerForm(){
        return "register";
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        model.addAttribute("username",principal.getName());
        return "dashboard";
    }
    @PostMapping("/saveUser")
    public String saveUser(@RequestParam String username, @RequestParam String password,
                           @RequestParam String email) {

        Users user = new Users(
                username,
                passwordEncoder.encode(password),
                email,
                "USER"
        );

        userRepository.save(user);

        return "redirect:/login";
    }

    //Student details controller

    @GetMapping("/list")
    public String listStudents(Model model){
        model.addAttribute("students", studentsService.getAllStudents());
        return "list";
    }


    // Show add form
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Students());
        return "/new";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute  Students student){
        studentsService.saveStudent(student);
        return "redirect:/list";
    }
    //edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Students student = studentsService.getStudentById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        model.addAttribute("student", student);
        return "edit";
    }


    // Update student
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Integer id, @ModelAttribute Students student) {
        studentsService.updateStudent(id, student);
        return "redirect:/list";
    }

    // Delete student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentsService.deleteStudent(id);
        return "redirect:/list";
    }

}