package com.hc.todoproje.Service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hc.todoproje.Repository.RoleRepository;
import com.hc.todoproje.Repository.UserRepository;
import com.hc.todoproje.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SmsService smsService;
	@Autowired
	private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {
        user.setPassword(user.getPassword());
        user.setRoles(roleRepository.findAll());
        
        String uuid=UUID.randomUUID().toString();
    	user.setVerifyKey(uuid);
    	String token;
		try {
			token = smsService.smsSenderFunction(user.getPhone(),user.getTokensms());
			  user.setTokensms(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
    	emailService.mailSenderFunction(user.getUsername(), user.getVerifyKey());
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public boolean findByverifyKey(String verifyKey) {
    	User user = userRepository.findByverifyKey(verifyKey);
    	
    	if(user!=null) {
    		user.setActive(true);
    		userRepository.save(user);
    		return true;
    	}else {
    		return false;
    	}
    }
    public void updateResetPasswordToken(String token,String username) throws UsernameNotFoundException {
    	User user=userRepository.findByUsername(username);
    	
    	if(user!= null) {
    		user.setResetPasswordToken(token);
    		userRepository.save(user);
    	}else 
    	{
    		throw new UsernameNotFoundException("Olmadı be" +username);
    	}
    }
    public User getByResetPasswordToken(String resetPasswordToken) {
    	return userRepository.findByResetPasswordToken(resetPasswordToken);
    }
    public void updatePassword(String newPassword, User user) {
    	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    	String encodePassword=passwordEncoder.encode(newPassword);
    	
    	user.setPassword(encodePassword);
    	user.setResetPasswordToken(null);
    	
    	userRepository.save(user);
    	
    }
    

}