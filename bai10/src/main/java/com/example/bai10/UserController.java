package com.example.bai10;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }


    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") User user,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        userService.save(user);
        return "result";
    }
}
