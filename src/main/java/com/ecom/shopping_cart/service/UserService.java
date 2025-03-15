package com.ecom.shopping_cart.service;


import com.ecom.shopping_cart.model.UserDtls;

import java.util.List;

public interface UserService {
    public UserDtls getUserById(int id);
    public UserDtls saveUser(UserDtls user);
    public UserDtls getUserByEmail(String email);
    public List<UserDtls> getUsers(String role);
    public Boolean updateAccountStatus(Integer userId, Boolean status);
    public void increaseFailedAttempt(UserDtls user);
    public void userAccountLock(UserDtls user);
    public boolean unlockAccountTimeExpired(UserDtls user);
    public void resetAttempt(int  userId);
    public void updateUserResetToken(String email,String resetToken);
    public UserDtls getUserByToken(String token);
    public List<UserDtls> getAllUser();

}
