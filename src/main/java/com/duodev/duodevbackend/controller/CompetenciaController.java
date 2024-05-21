package com.duodev.duodevbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duodev.duodevbackend.model.Competencia;
import com.duodev.duodevbackend.repository.CompetenciaRepository;

@RestController
public class CompetenciaController {

    private final CompetenciaRepository competenciaRepository;

  public CompetenciaController(CompetenciaRepository competenciaRepository) {
    this.competenciaRepository = competenciaRepository;
  }
  
  @GetMapping("/competencia")
  public Iterable<Competencia> findAllCompetencias() {
    return this.competenciaRepository.findAll();
  }

  @PostMapping("/competencia")
  public Competencia addOneCompetencia(@RequestBody Competencia competencia) {
    return this.competenciaRepository.save(competencia);
  }
    
}
