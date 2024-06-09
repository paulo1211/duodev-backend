package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.repository.UsuarioCompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioCompetenciaService {

    @Autowired
    private UsuarioCompetenciaRepository usuarioCompetenciaRepository;

    public UsuarioCompetencia createUsuarioCompetencia(UsuarioCompetencia usuarioCompetencia) {
        return usuarioCompetenciaRepository.save(usuarioCompetencia);
    }

    public UsuarioCompetencia updateUsuarioCompetencia(UsuarioCompetencia usuarioCompetencia) {
        return usuarioCompetenciaRepository.save(usuarioCompetencia);
    }

    public void deleteUsuarioCompetenciaById(int id) {
        usuarioCompetenciaRepository.deleteById(id);
    }

    public UsuarioCompetencia getUsuarioCompetenciaById(int id) {
        return usuarioCompetenciaRepository.findById(id).orElse(null);
    }

    public Iterable<UsuarioCompetencia> getAllUsuarioCompetencia() {
        return usuarioCompetenciaRepository.findAll();
    }

    public List<UsuarioCompetencia> findUsuariosByCompetenciaId(int competenciaId, Integer anosExpMin) {
        return usuarioCompetenciaRepository.findByCompetenciaId(competenciaId, anosExpMin);
    }
}
