package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class UsuarioAreaInteresseId implements Serializable {
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "id_competencia")
    private int idCompetencia;
}