package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.exceptions.ResourceNotFoundException;
import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    public Sessao createSessao(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    public List<Sessao> getAllSessoes() {
        return sessaoRepository.findAll();
    }

    public Sessao getSessaoById(int id) {
        return sessaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sessao not found"));
    }
    public Sessao updateSessao(int id, Sessao sessaoDetails) {
        Sessao sessao = getSessaoById(id);
        sessao.setDataHoraInicial(sessaoDetails.getDataHoraInicial());
        sessao.setDataHoraFinal(sessaoDetails.getDataHoraFinal());
        sessao.setStatus(sessaoDetails.getStatus());
        sessao.setMentoria(sessaoDetails.getMentoria());

        return sessaoRepository.save(sessao);
    }

    public void deleteSessao(int id) {
        Sessao sessao = getSessaoById(id);
        sessaoRepository.delete(sessao);
    }
}