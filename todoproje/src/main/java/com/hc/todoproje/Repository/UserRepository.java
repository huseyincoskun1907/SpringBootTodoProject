package com.hc.todoproje.Repository;

import org.springframework.data.repository.CrudRepository;

import com.hc.todoproje.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByverifyKey(String verifyKey);
   // User update(User user);
    public User findByResetPasswordToken(String token);
    User findByPhone(String phone);
	  User findByTokensms(String  tokensms);
}