package com.hc.todoproje.Service;

import com.hc.todoproje.entity.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
     public boolean findByverifyKey(String verifyKey); 
    public void updateResetPasswordToken(String token,String username);
    public void updatePassword(String password,User user);
   User getByResetPasswordToken(String resetPasswordToken);

}