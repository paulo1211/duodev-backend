package com.duodev.duodevbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "mentoria")
@Getter
@Setter
public class Mentoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario_mentorado")
    private Usuario usuarioMentorado;

    @ManyToOne
    @JoinColumn(name = "id_usuario_mentor")
    private Usuario usuarioMentor;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicial;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFinal;

    private int pontuacaoMentor;

    private int pontuacaoMentorado;

}
