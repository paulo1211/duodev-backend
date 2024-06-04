package com.duodev.duodevbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duodev.duodevbackend.model.UsuarioAreaInteresse;
import com.duodev.duodevbackend.service.UsuarioAreaInteresseService;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InteresseUsuario {

    @Autowired
    private UsuarioAreaInteresseService usuarioAreaInteresseService;

    @GetMapping
    public Iterable<UsuarioAreaInteresse> getAllInterests() {
        return usuarioAreaInteresseService.getAllUsuarioAreaInteresse();
    }

    @PostMapping
    public UsuarioAreaInteresse createInterest(@RequestBody UsuarioAreaInteresse interest) {
        return usuarioAreaInteresseService.createUsuarioAreaInteresse(interest);
    }

    @PutMapping
    public UsuarioAreaInteresse updateInterest(@RequestBody UsuarioAreaInteresse interest) {
        return usuarioAreaInteresseService.updateUsuarioAreaInteresse(interest);
    }

    @DeleteMapping("/{id}")
    public void deleteInterest(@PathVariable int id) {
        usuarioAreaInteresseService.deleteUsuarioAreaInteresseById(id);
    }

    @GetMapping("/{id}")
    public UsuarioAreaInteresse getInterestById(@PathVariable int id) {
        return usuarioAreaInteresseService.getUsuarioAreaInteresseById(id);
    }
}
