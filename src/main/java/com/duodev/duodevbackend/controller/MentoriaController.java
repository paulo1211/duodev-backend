package com.duodev.duodevbackend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.duodev.duodevbackend.model.Mentoria;
import com.duodev.duodevbackend.repository.MentoriaRepository;

@RestController
public class MentoriaController {

    private final MentoriaRepository mentoriaRepository;

  public MentoriaController(MentoriaRepository mentoriaRepository) {
    this.mentoriaRepository = mentoriaRepository;
  }
  
  @GetMapping("/mentoria")
  public List<Mentoria> findAllMentorias() {
    return this.mentoriaRepository.findAll();
  }

  @PostMapping("/mentoria")
  public Mentoria Mentoria(@RequestBody Mentoria mentoria) {
    return this.mentoriaRepository.save(mentoria);
  }
    
}
