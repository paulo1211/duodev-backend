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

import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.service.UsuarioCompetenciaService;

import java.util.List;

@RestController
@RequestMapping("/api/competencias")
public class CompetenciaMentor {

    @Autowired
    private UsuarioCompetenciaService usuarioCompetenciaService;

    @GetMapping
    public Iterable<UsuarioCompetencia> getAllInterests() {
        return usuarioCompetenciaService.getAllUsuarioCompetencia();
    }

    @PostMapping
    public UsuarioCompetencia createInterest(@RequestBody UsuarioCompetencia interest) {
        return usuarioCompetenciaService.createUsuarioCompetencia(interest);
    }

    @PutMapping
    public UsuarioCompetencia updateInterest(@RequestBody UsuarioCompetencia interest) {
        return usuarioCompetenciaService.updateUsuarioCompetencia(interest);
    }

    @DeleteMapping("/{id}")
    public void deleteInterest(@PathVariable int id) {
        usuarioCompetenciaService.deleteUsuarioCompetenciaById(id);
    }

    @GetMapping("/{id}")
    public UsuarioCompetencia getInterestById(@PathVariable int id) {
        return usuarioCompetenciaService.getUsuarioCompetenciaById(id);
    }
}
