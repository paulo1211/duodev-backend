package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.exceptions.ResourceNotFoundException;
import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.model.Competencia;
import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.repository.UsuarioCompetenciaRepository;
import com.duodev.duodevbackend.repository.UsuarioRepository;
import com.duodev.duodevbackend.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioCompetenciaService {

    @Autowired
    private UsuarioCompetenciaRepository usuarioCompetenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    public UsuarioCompetencia addCompetenciaToUsuario(int usuarioId, int competenciaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
        Competencia competencia = competenciaRepository.findById(competenciaId).orElseThrow(() -> new ResourceNotFoundException("Competencia not found"));

        UsuarioCompetencia usuarioCompetencia = new UsuarioCompetencia();
        usuarioCompetencia.setUsuario(usuario);
        usuarioCompetencia.setCompetencia(competencia);

        return usuarioCompetenciaRepository.save(usuarioCompetencia);
    }

    public List<UsuarioCompetencia> addCompetenciasToUsuario(int usuarioId, List<Integer> competenciaIds) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));

        List<Competencia> competencias = competenciaRepository.findAllById(competenciaIds);
        List<UsuarioCompetencia> usuarioCompetencias = competencias.stream().map(competencia -> {
            UsuarioCompetencia usuarioCompetencia = new UsuarioCompetencia();
            usuarioCompetencia.setUsuario(usuario);
            usuarioCompetencia.setCompetencia(competencia);
            return usuarioCompetencia;
        }).collect(Collectors.toList());

        return usuarioCompetenciaRepository.saveAll(usuarioCompetencias);
    }

    public void removeCompetenciaFromUsuario(int usuarioId, int competenciaId) {
        usuarioCompetenciaRepository.deleteByUsuarioIdAndCompetenciaId(usuarioId, competenciaId);
    }
}
