package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.dto.SessaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.service.SessaoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

  @Autowired
  private SessaoService sessaoService;

  @PostMapping
  public ResponseEntity<String> adicionarSessao(@RequestBody SessaoDto sessaoDto)
          throws Exception {
    String sessaoCriada = sessaoService.createSessao(sessaoDto.sessao(), sessaoDto.emailMentor(),
            sessaoDto.emailMentorado());
    return ResponseEntity.status(HttpStatus.CREATED).body(sessaoCriada);
  }

  @GetMapping
  public ResponseEntity<List<Sessao>> listarSessoes() throws IOException {

    return ResponseEntity.ok(sessaoService.getAllSessoes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Sessao> obterSessao(@PathVariable Integer id) {
    Sessao sessao = sessaoService.getSessaoById(id);
    return ResponseEntity.ok(sessao);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> atualizarSessao(@PathVariable int id, @RequestBody Sessao sessao) throws IOException {
    String sessaoAtualizada = sessaoService.updateSessao(id, sessao);
    return ResponseEntity.ok(sessaoAtualizada);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletarSessao(@PathVariable Integer id) {
    Optional<Sessao> existingSessao = Optional.ofNullable(sessaoService.getSessaoById(id));
    if (existingSessao.isPresent()) {
      sessaoService.deleteSessao(existingSessao.get().getId());
      return ResponseEntity.ok().body("Sessão cancelada com sucesso!");
    }
    return ResponseEntity.notFound().build();

  }


}
