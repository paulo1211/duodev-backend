package com.duodev.duodevbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duodev.duodevbackend.model.Competencia;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InteresseUsuario {
    @Autowired
    private InteresseUsuario InteresseUsuario;

    @GetMapping
    public List<Competencia> getAllInterests() {
        return InteresseUsuario.getAllInterests();
    }

    @PostMapping
    public Competencia saveInterest(@RequestBody Competencia interest) {
        return InteresseUsuario.saveInterest(interest);
    }
}
