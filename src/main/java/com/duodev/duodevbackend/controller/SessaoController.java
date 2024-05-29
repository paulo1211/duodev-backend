package com.duodev.duodevbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.service.SessaoService;

import java.util.List;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

  @Autowired
  private SessaoService sessaoService;

  @PostMapping
  public ResponseEntity<Sessao> adicionarSessao(@RequestBody Sessao sessao) {
    Sessao sessaoCriada = sessaoService.createSessao(sessao);
    return ResponseEntity.status(HttpStatus.CREATED).body(sessaoCriada);
  }

  @GetMapping
  public ResponseEntity<List<Sessao>> listarSessoes() {
    List<Sessao> sessoes = sessaoService.getAllSessoes();
    return ResponseEntity.ok(sessoes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Sessao> obterSessao(@PathVariable Integer id) {
    Sessao sessao = sessaoService.getSessaoById(id);
    return ResponseEntity.ok(sessao);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Sessao> atualizarSessao(@PathVariable Integer id, @RequestBody Sessao sessao) {
    Sessao sessaoAtualizada = sessaoService.updateSessao(id, sessao);
    return ResponseEntity.ok(sessaoAtualizada);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarSessao(@PathVariable Integer id) {
    sessaoService.deleteSessao(id);
    return ResponseEntity.noContent().build();
  }
}