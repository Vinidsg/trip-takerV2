package com.senac.tripTaker.service;

import com.senac.tripTaker.model.User;
import com.senac.tripTaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean verifyCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).isPresent();
    }


}
