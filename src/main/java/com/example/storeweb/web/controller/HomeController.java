package com.example.storeweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        return "registerForm";
    }

    @PostMapping("/register")
    public String register() {
        return "redirect:/completed";
    }

    @GetMapping("/completed")
    public String registerCompleted() {
        return "completed";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "loginForm";
    }
}
