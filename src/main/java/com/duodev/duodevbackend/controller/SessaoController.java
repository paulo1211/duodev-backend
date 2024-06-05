package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.dto.SessaoDto;
import com.duodev.duodevbackend.enums.Status;
import com.duodev.duodevbackend.model.Mentoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.service.SessaoService;

import java.io.IOException;
import java.time.LocalDateTime;
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

  @GetMapping("/{id}")
  public ResponseEntity<Sessao> obterSessao(@PathVariable Integer id) {
    Sessao sessao = sessaoService.getSessaoById(id);
    return ResponseEntity.ok(sessao);
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

    @GetMapping("/mentoria/{id}")
    public ResponseEntity<List<Sessao>> obterSessoesPorMentoria(@PathVariable Mentoria mentoria) {
        List<Sessao> sessoes = sessaoService.getSessoesByMentoria(mentoria);
        return ResponseEntity.ok(sessoes);
    }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<Sessao>> obterSessoesPorStatus(@PathVariable String status) {
    Status statusEnum = Status.valueOf(status.toUpperCase());
    List<Sessao> sessoes = sessaoService.getSessoesByStatus(statusEnum);
    return ResponseEntity.ok(sessoes);
  }

    @GetMapping("/dataHoraInicial/{dataHoraInicial}")
    public ResponseEntity<List<Sessao>> obterSessoesPorDataHoraInicial(@PathVariable String dataHoraInicial) {
        List<Sessao> sessoes = sessaoService.findByDataHoraInicial(LocalDateTime.parse(dataHoraInicial));
        return ResponseEntity.ok(sessoes);
    }

    @GetMapping("/dataHoraFinal/{dataHoraFinal}")
    public ResponseEntity<List<Sessao>> obterSessoesPorDataHoraFinal(@PathVariable String dataHoraFinal) {
        List<Sessao> sessoes = sessaoService.findByDataHoraFinal(LocalDateTime.parse(dataHoraFinal));
        return ResponseEntity.ok(sessoes);
    }

    @GetMapping("/dataHoraInicial/{dataHoraInicial}/dataHoraFinal/{dataHoraFinal}")
    public ResponseEntity<List<Sessao>> obterSessoesPorDataHoraEntre(@PathVariable String dataHoraInicial,
                                                                     @PathVariable String dataHoraFinal) {
        List<Sessao> sessoes = sessaoService.findByDataHoraInicialBetween(LocalDateTime.parse(dataHoraInicial),
                LocalDateTime.parse(dataHoraFinal));
        return ResponseEntity.ok(sessoes);
    }

}
