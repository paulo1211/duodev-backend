package com.duodev.duodevbackend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.repository.UsuarioRepository;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

  public UsuarioController(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }
  
  @GetMapping("/usuario")
  public List<Usuario> findAllUsuarios() {
    return this.usuarioRepository.findAll();
  }

  @PostMapping("/usuario")
  public Usuario Usuario(@RequestBody Usuario usuario) {
    return this.usuarioRepository.save(usuario);
  }
    
}
