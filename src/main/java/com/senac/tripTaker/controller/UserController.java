package com.senac.tripTaker.controller;

import com.senac.tripTaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        boolean isValidUser = userService.verifyCredentials(username, password);

        if (isValidUser) {
            session.setAttribute("username", username);
            return "redirect:/gerenciar-trips"; // Ajuste conforme suas necessidades
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Usuário ou Senha inválidos!");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
