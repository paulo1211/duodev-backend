package com.duodev.duodevbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Email {

    @Id
    private Long id;

    @NotNull
    private String sendTo;
    @NotNull
    private String sendFrom;
    @NotNull
    private String subject;
    @NotNull
    private String body;

    @NotNull
    private LocalDateTime sendDate;


}
