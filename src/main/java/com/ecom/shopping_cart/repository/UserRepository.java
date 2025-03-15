package com.ecom.shopping_cart.repository;

import com.ecom.shopping_cart.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

    public List<UserDtls> findByRole(String role);
    public UserDtls findByResetToken(String restToken);
    public  UserDtls findByEmail(String email);

}
