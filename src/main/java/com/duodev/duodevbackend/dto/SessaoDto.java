package com.duodev.duodevbackend.dto;

import com.duodev.duodevbackend.model.Sessao;

public record SessaoDto(Sessao sessao, String emailMentor, String emailMentorado) {

}
