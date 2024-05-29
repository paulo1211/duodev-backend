package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.service.UsuarioCompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-competencia")
public class UsuarioCompetenciaController {

    @Autowired
    private UsuarioCompetenciaService usuarioCompetenciaService;

    @PostMapping("/{usuarioId}/competencia/{competenciaId}")
    public ResponseEntity<UsuarioCompetencia> addCompetenciaToUsuario(@PathVariable int usuarioId, @PathVariable int competenciaId) {
        UsuarioCompetencia usuarioCompetencia = usuarioCompetenciaService.addCompetenciaToUsuario(usuarioId, competenciaId);
        return ResponseEntity.ok(usuarioCompetencia);
    }

    @PostMapping("/{usuarioId}/competencias")
    public ResponseEntity<List<UsuarioCompetencia>> addCompetenciasToUsuario(@PathVariable int usuarioId, @RequestBody List<Integer> competenciaIds) {
        List<UsuarioCompetencia> usuarioCompetencias = usuarioCompetenciaService.addCompetenciasToUsuario(usuarioId, competenciaIds);
        return ResponseEntity.ok(usuarioCompetencias);
    }

    @DeleteMapping("/{usuarioId}/competencia/{competenciaId}")
    public ResponseEntity<Void> removeCompetenciaFromUsuario(@PathVariable int usuarioId, @PathVariable int competenciaId) {
        usuarioCompetenciaService.removeCompetenciaFromUsuario(usuarioId, competenciaId);
        return ResponseEntity.noContent().build();
    }
}
