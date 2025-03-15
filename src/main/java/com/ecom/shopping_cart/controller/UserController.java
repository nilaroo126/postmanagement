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

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public String index() {
        return "user/home";
    }

    @GetMapping("/profile")
    public String profile(@ModelAttribute UserDtls user, Model model) {
        UserDtls userDtls = userService.getUserByEmail(user.getEmail());
        model.addAttribute("user", userDtls);
        return "user/profile";
    }

    @GetMapping("/create")
    public String createProjects(HttpSession session){
        return "user/create_project";
    }

    @PostMapping("/savePost")
    public String savePost(@ModelAttribute Projects projects, HttpSession session){
        projects.setCreated_at(LocalDate.now());
        projectService.saveProject(projects);
        return "redirect:/user/";
    }

    @GetMapping("/viewProject")
    public String viewProject( Model m){
        List<Projects> pro = projectService.getAllProject();
        m.addAttribute("project", pro);
        return "user/view_project";
    }

    @GetMapping("/viewProjectById/{id}")
    public String viewById(@PathVariable int id, Model m){
        Projects p = projectService.getProjectById(id);
        m.addAttribute("project", p);
        return "user/view_details";
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable int id, HttpSession session){
        Boolean deletedProject = projectService.deleteProject(id);
        if (deletedProject) {
            session.setAttribute("succMsg", "Project delete success");
        } else {
            session.setAttribute("errorMsg", "something wrong on server");
        }
        return "redirect:/user/viewProject";
    }

    @GetMapping("/loadUpdateProject/{id}")
    public String loadUpdateProject(@PathVariable int id,Model model){
        Projects pro = projectService.getProjectById(id);
        model.addAttribute("project", pro);
        return "user/update_project";
    }

    @PostMapping("/updateProject")
    public String updateProject( @ModelAttribute Projects projects){
        projectService.updateProject(projects);
        return "redirect:/user/viewProject";
    }
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String s,  Model model ){
        List<Projects> projects = projectService.searchByIdOrTitle(s);
        model.addAttribute("project", projects);
        return "user/view_project";
    }

}
