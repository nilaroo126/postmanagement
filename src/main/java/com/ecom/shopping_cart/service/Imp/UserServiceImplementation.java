package com.ecom.shopping_cart.service.Imp;

import com.ecom.shopping_cart.model.UserDtls;
import com.ecom.shopping_cart.repository.UserRepository;
import com.ecom.shopping_cart.service.UserService;
import  com.ecom.shopping_cart.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation  implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDtls getUserById(int id) {
        UserDtls user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public UserDtls saveUser(UserDtls user) {
        user.setRole("ROLE_USER");
        user.setIsEnable(true);
        user.setAccountNonLocked(true);
        user.setFailedAttempt(0);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(user.getIsEnable());
     return userRepository.save(user);

    }

    @Override
    public UserDtls getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    @Override
    public List<UserDtls> getUsers(String role) {
        return userRepository.findByRole(role);
    }


    @Override
    public Boolean updateAccountStatus(Integer userId, Boolean status) {
        Optional<UserDtls> findByUser = userRepository.findById(userId);
        if (findByUser.isPresent()) {
            UserDtls userDtls = findByUser.get();
            userDtls.setIsEnable(status);
            userRepository.save(userDtls);
            return true;
        }

        return false;
    }

    @Override
    public void increaseFailedAttempt(UserDtls user) {
    int failedAttempt = user.getFailedAttempt() + 1;
    user.setFailedAttempt(failedAttempt);
    userRepository.save(user);
    }

    @Override
    public void userAccountLock(UserDtls user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        userRepository.save(user);


    }

    @Override
    public boolean unlockAccountTimeExpired(UserDtls user) {
        long lockTime = user.getLockTime().getTime();
        long unLockTime = lockTime + AppConstant.UNLOCK_DURATION_TIME;
        long currentTime = System.currentTimeMillis();
        if(currentTime > unLockTime) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void resetAttempt(int userId) {

    }

    @Override
    public void updateUserResetToken(String email, String resetToken) {

            UserDtls findByEmail = userRepository.findByEmail(email);
            findByEmail.setResetToken(resetToken);
            userRepository.save(findByEmail);
    }

    @Override
    public UserDtls getUserByToken(String token) {
        return userRepository.findByResetToken(token);
    }

    @Override
    public List<UserDtls> getAllUser() {
        return userRepository.findAll();
    }




}
