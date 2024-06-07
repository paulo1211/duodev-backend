package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String sendTo;

    @NotNull
    private String sendFrom;

    @NotNull
    private String requestedBy;

    @NotNull
    private String subject;

    @NotNull
    private String body;

    @NotNull
    private LocalDateTime sendDate;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Sessao sessao;

    private String inviteId;


    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", sendTo='" + sendTo + '\'' +
                ", sendFrom='" + sendFrom + '\'' +
                ", requestedBy='" + requestedBy + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", sendDate=" + sendDate +
                ", usuario=" + usuario +
                ", sessao=" + sessao +
                ", inviteId='" + inviteId + '\'' +
                '}';
    }
}
