package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.Mentoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface MentoriaRepository extends JpaRepository<Mentoria, Integer> {
    List<Mentoria> findByIdUsuarioMentor(int idUsuarioMentor);
    List<Mentoria> findByIdUsuarioMentorado(int idUsuarioMentorado);
}
