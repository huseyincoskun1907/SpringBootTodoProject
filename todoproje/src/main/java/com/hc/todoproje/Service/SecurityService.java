package com.hc.todoproje.Service;

public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);
}