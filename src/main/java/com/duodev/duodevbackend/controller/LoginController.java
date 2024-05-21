package com.duodev.duodevbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duodev.duodevbackend.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginForm() {
        return "publica-login-usuario.html"; 
    }

    @PostMapping("/login")
    public String loginSubmit(Model model, @RequestParam String username, @RequestParam String password) {
        if (usuarioService.autenticarUsuario(username, password)) {
            // Se as credenciais estiverem corretas, redireciona para a página inicial
            return "redirect:/";
        } else {
            model.addAttribute("error", "Credenciais inválidas");
            return "login"; // Retorna para a página de login com uma mensagem de erro
        }
    }
}
