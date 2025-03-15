package com.ecom.shopping_cart.controller;


import com.ecom.shopping_cart.model.Projects;
import com.ecom.shopping_cart.model.UserDtls;
import com.ecom.shopping_cart.service.ProjectService;
import com.ecom.shopping_cart.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private  UserService userService;
    @Autowired
    private ProjectService projectService;

    @ModelAttribute
    public void getUserDetails(Principal p, Model model) {
        if(p != null) {
            String email = p.getName();
            UserDtls userDtls = userService.getUserByEmail(email);
            model.addAttribute("user", userDtls);

        }
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/signin")
    public String login(UserDtls user){
        System.out.println(userService.getUserByEmail(user.getEmail()));
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") UserDtls user, HttpSession session){
        userService.saveUser(user);
        return "redirect:/register";
    }


}
