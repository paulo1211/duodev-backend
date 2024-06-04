package com.duodev.duodevbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.service.UsuarioService;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/api/usuarios")
    public ResponseEntity<String> adicionarUsuario(@RequestBody Usuario usuario) {
        usuarioService.adicionarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário adicionado com sucesso!");
    }
}