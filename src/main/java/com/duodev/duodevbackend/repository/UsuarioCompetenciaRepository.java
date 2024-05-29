package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.UsuarioCompetencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioCompetenciaRepository extends JpaRepository<UsuarioCompetencia, Integer> {
    void deleteByUsuarioIdAndCompetenciaId(int usuarioId, int competenciaId);
}
