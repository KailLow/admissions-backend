package com.otters.admissionsbackend.utils;

import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    private static LoginManager instance;
    private String loggedInUser;

    public LoginManager(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public LoginManager() {}

    public static synchronized LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public synchronized void login(String username) throws Exception {
        if (loggedInUser == null) {
            loggedInUser = username;
            System.out.println("User " + username + " logged in successfully.");
        } else {
            throw new Exception("Another user is already logged in.");
        }
    }

    public synchronized void logout() {
        loggedInUser = null;
        System.out.println("User logged out successfully.");
    }

    public synchronized boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public synchronized String getLoggedInUser() {
        return loggedInUser;
    }
}
