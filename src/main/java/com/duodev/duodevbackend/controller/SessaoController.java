package com.duodev.duodevbackend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.repository.SessaoRepository;

@RestController
public class SessaoController {

    private final SessaoRepository sessaoRepository;

  public SessaoController(SessaoRepository sessaoRepository) {
    this.sessaoRepository = sessaoRepository;
  }
  
  @GetMapping("/sessao")
  public List<Sessao> findAllSessoes() {
    return this.sessaoRepository.findAll();
  }

  @PostMapping("/sessao")
  public Sessao Sessao(@RequestBody Sessao sessao) {
    return this.sessaoRepository.save(sessao);
  }
    
}
