package com.senac.tripTaker.controller;

import com.senac.tripTaker.model.User;
import com.senac.tripTaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@RequestBody User credentials, HttpSession session) {
        boolean isValidUser = userService.verifyCredentials(credentials.getUsername(), credentials.getPassword());

        if (isValidUser) {
            session.setAttribute("username", credentials.getUsername());
            // Retorna um objeto JSON indicando sucesso no login.
            return ResponseEntity.ok().body(Map.of("message", "Login bem-sucedido."));
        } else {
            // Retorna um objeto JSON indicando falha no login.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuário ou Senha inválidos!"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> handleLogout(HttpSession session) {
        session.invalidate();
        // Retorna um objeto JSON indicando que o logout foi bem-sucedido.
        return ResponseEntity.ok().body(Map.of("message", "Logout bem-sucedido."));
    }
}
