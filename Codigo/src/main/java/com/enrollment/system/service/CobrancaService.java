package com.enrollment.system.service;

import com.enrollment.system.models.Cobranca;
import com.enrollment.system.models.Matricula;
import com.enrollment.system.enums.StatusCobranca;
import com.enrollment.system.repository.CobrancaRepository;
import com.enrollment.system.repository.MatriculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CobrancaService {

    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public Cobranca gerarCobranca(Long matriculaId, BigDecimal valor) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        Cobranca cobranca = new Cobranca();
        cobranca.setMatricula(matricula);
        cobranca.setValor(valor);
        cobranca.setStatus(StatusCobranca.PENDENTE);
        cobranca.setDataGeracao(LocalDateTime.now());

        return cobrancaRepository.save(cobranca);
    }

    // notificarAluno() : void
    public void notificarAluno(Long cobrancaId) {
    }

    // verificarStatus() : StatusCobranca
    public StatusCobranca verificarStatus(Long cobrancaId) {
        Cobranca cobranca = cobrancaRepository.findById(cobrancaId)
                .orElseThrow(() -> new RuntimeException("Cobrança não encontrada"));

        return cobranca.getStatus();
    }

    // realizarPagamento() : void
    public void realizarPagamento(Long cobrancaId) {
        Cobranca cobranca = cobrancaRepository.findById(cobrancaId)
                .orElseThrow(() -> new RuntimeException("Cobrança não encontrada"));

        cobranca.setStatus(StatusCobranca.PAGA);
        cobrancaRepository.save(cobranca);
    }
}