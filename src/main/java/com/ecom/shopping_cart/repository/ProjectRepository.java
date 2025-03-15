package com.ecom.shopping_cart.repository;

import com.ecom.shopping_cart.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository  extends JpaRepository<Projects,Integer> {
    List<Projects> findByIdOrTitleContainingIgnoreCase(Integer id, String title);

}
