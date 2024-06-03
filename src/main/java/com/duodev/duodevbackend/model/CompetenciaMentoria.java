package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "competenciamentoria")
@Getter
@Setter
public class CompetenciaMentoria {
    @EmbeddedId
    private CompetenciaMentoriaId id;
    
    @ManyToOne
    @MapsId("idMentoria")
    @JoinColumn(name = "id_mentoria")
    private int mentoria;

    @ManyToOne
    @MapsId("idCompetencia")
    @JoinColumn(name = "id_competencia")
    private String competencia;

    
    public int getInt() {
        return mentoria;
    }

    public void setId(int mentoria) {
        this.mentoria = mentoria;
    }

    public String getcompetencia() {
        return competencia;
    }

    public void setcompetencia(String competencia) {
        this.competencia = competencia;
    }
}
