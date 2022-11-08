package com.example.tvtime.user;

import com.google.firebase.auth.FirebaseUser;

public class UserManager {
    private static volatile UserManager instance;
    private static UserRepository userRepository;

    private UserManager() {
        userRepository = UserRepository.getInstance();
    }

    public static UserManager getInstance() {
        UserManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized(UserRepository.class) {
            if (instance == null) {
                instance = new UserManager();
            }
            return instance;
        }
    }

    public FirebaseUser getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public static void logOut(){
        userRepository.logOut();
    }

    public Boolean isCurrentUserLogged(){
        return (this.getCurrentUser() != null);
    }
}
