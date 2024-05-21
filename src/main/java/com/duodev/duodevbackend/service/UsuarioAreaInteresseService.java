package com.duodev.duodevbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duodev.duodevbackend.model.UsuarioAreaInteresse;
import com.duodev.duodevbackend.repository.UsuarioAreaInteresseRepository;

@Service
public class UsuarioAreaInteresseService {

    @Autowired
    private UsuarioAreaInteresseRepository usuarioAreaInteresseRepository;

    public UsuarioAreaInteresse createUsuarioAreaInteresse(UsuarioAreaInteresse usuarioAreaInteresse) {
        return usuarioAreaInteresseRepository.save(usuarioAreaInteresse);
    }

    public UsuarioAreaInteresse updateUsuarioAreaInteresse(UsuarioAreaInteresse usuarioAreaInteresse) {
        return usuarioAreaInteresseRepository.save(usuarioAreaInteresse);
    }

    public void deleteUsuarioAreaInteresseById(int id) {
        usuarioAreaInteresseRepository.deleteById(id);
    }

    public UsuarioAreaInteresse getUsuarioAreaInteresseById(int id) {
        return usuarioAreaInteresseRepository.findById(id).orElse(null);
    }

    public Iterable<UsuarioAreaInteresse> getAllUsuarioAreaInteresse() {
        return usuarioAreaInteresseRepository.findAll();
    }


}
