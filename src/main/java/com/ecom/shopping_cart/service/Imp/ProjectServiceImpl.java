package com.ecom.shopping_cart.service.Imp;


import com.ecom.shopping_cart.model.Projects;
import com.ecom.shopping_cart.model.UserDtls;
import com.ecom.shopping_cart.repository.ProjectRepository;
import com.ecom.shopping_cart.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Projects saveProject(Projects project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Projects> getAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public Boolean deleteProject(int id) {
        Projects project = projectRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(project)){
            projectRepository.delete(project);
            return true;
        }
        return false;
    }
    @Override
    public Projects getProjectById(int id) {
        Projects project = projectRepository.findById(id).orElse(null);
        return project;
    }


    @Override
    public Projects updateProject(Projects project) {
        Projects dbProject = getProjectById(project.getId());
        dbProject.setTitle(project.getTitle());
        dbProject.setDescription(project.getDescription());
        dbProject.setUpdated_at(LocalDate.now());
        return projectRepository.save(dbProject);
    }

    @Override
    public List<Projects> searchByIdOrTitle(String input) {
        // Check if the input is a number (ID)
        try {
            Integer id = Integer.parseInt(input);
            return projectRepository.findByIdOrTitleContainingIgnoreCase(id, null);
        } catch (NumberFormatException e) {
            // If input is not a number, treat it as a title
            return projectRepository.findByIdOrTitleContainingIgnoreCase(null, input);
        }
    }


}
