package com.senac.tripTaker.controller;

import com.senac.tripTaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Este método pode ser removido ou substituído por uma API que retorna informações de status.
    @GetMapping("/login")
    public ResponseEntity<Void> showLoginForm() {
        // Pode retornar um status simples ou informações sobre como fazer login.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleLogin(@RequestBody UserCredentials credentials, HttpSession session) {
        boolean isValidUser = userService.verifyCredentials(credentials.getUsername(), credentials.getPassword());

        if (isValidUser) {
            session.setAttribute("username", credentials.getUsername());
            // Retorna uma resposta indicando sucesso no login.
            return ResponseEntity.ok("Login bem-sucedido.");
        } else {
            // Retorna uma resposta indicando falha no login.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou Senha inválidos!");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> handleLogout(HttpSession session) {
        session.invalidate();
        // Retorna uma resposta indicando que o logout foi bem-sucedido.
        return ResponseEntity.ok("Logout bem-sucedido.");
    }

    // Classe interna para modelar as credenciais do usuário
    private static class UserCredentials {
        private String username;
        private String password;

        // Getters e setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
