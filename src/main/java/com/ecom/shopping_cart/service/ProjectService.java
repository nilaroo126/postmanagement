package com.ecom.shopping_cart.service;

import com.ecom.shopping_cart.model.Projects;
import com.ecom.shopping_cart.model.UserDtls;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    public Projects saveProject(Projects project);
    public List<Projects> getAllProject();
    public Boolean deleteProject(int id);
    public Projects updateProject(Projects project);
    public Projects getProjectById(int id);
    public List<Projects> searchByIdOrTitle(String n);


}
