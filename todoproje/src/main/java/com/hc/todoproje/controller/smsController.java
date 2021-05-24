package com.hc.todoproje.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hc.todoproje.Repository.UserRepository;
import com.hc.todoproje.Service.UserService;
import com.hc.todoproje.entity.User;

import javax.validation.Valid;
import java.io.Serializable;

@Controller
public class smsController implements Serializable {



	
    @Autowired
    private UserRepository userRepository;



    
    @RequestMapping(value = {"/sms"})
    public String home(){
        return "sms";
    }

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
	public String regsOk(String tokensms) {
		User usergelen=userRepository.findByTokensms(tokensms);
		if(usergelen.getTokensms().equals(tokensms)) {
			usergelen.setSmsEnabled(true);
			userRepository.save(usergelen);
		}
		return "redirect:/login";
		
	}

}