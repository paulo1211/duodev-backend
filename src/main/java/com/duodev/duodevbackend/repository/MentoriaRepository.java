package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.Mentoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoriaRepository extends JpaRepository<Mentoria, Integer> {
    List<Mentoria> findByIdUsuarioMentor(Integer idUsuarioMentor);

    List<Mentoria> findByIdUsuarioMentorado(int id);
}
