package com.senac.tripTaker.service;

import com.senac.tripTaker.model.User;
import com.senac.tripTaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerNewUserAccount(User user) throws Exception {
        if (emailExists(user.getEmail())) {
            throw new Exception("There is an account with that email address: " + user.getEmail());
        }
        if (usernameExists(user.getUsername())) {
            throw new Exception("There is an account with that username: " + user.getUsername());
        }
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean verifyCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).isPresent();
    }
}
