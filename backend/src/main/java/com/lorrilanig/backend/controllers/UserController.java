package com.lorrilanig.backend.controllers;

import com.lorrilanig.backend.models.User;
import com.lorrilanig.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }


    @PostMapping("new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userRepository.getReferenceById(id));
        return "user/form";
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "redirect:/users:";
    }
}

