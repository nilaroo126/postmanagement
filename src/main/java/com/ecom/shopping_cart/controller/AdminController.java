package com.ecom.shopping_cart.controller;


import com.ecom.shopping_cart.model.Projects;
import com.ecom.shopping_cart.model.UserDtls;
import com.ecom.shopping_cart.service.ProjectService;
import com.ecom.shopping_cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
  @Autowired
  private UserService userService;
  @Autowired
  private ProjectService projectService;

  @GetMapping("/")
    public String index(Model model) {
      return "admin/home";
  }
  @GetMapping("/profile")
  public String profile(@RequestParam String email,Model model) {
    UserDtls user = userService.getUserByEmail(email);
    model.addAttribute("user", user);
    return "admin/profile";
  }

  @GetMapping("/userList")
  public String users( Model model) {
    List<UserDtls> users = userService.getAllUser();
    model.addAttribute("users", users);
    return "admin/userList";
  }

  @GetMapping("/projectList")
  public String projects( Model model) {
    List<Projects> projectsList = projectService.getAllProject();
    System.out.println(projectsList);
    model.addAttribute("project", projectsList);
    return "admin/projectsList";
  }

  @GetMapping("/blackList_user")
  public String userBlackList(@ModelAttribute UserDtls user, Model model) {
    return "admin/blacklist_user";
  }


}
