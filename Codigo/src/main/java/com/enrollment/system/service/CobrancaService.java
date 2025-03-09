package com.enrollment.system.service;

import com.enrollment.system.models.Cobranca;
import com.enrollment.system.models.Matricula;
import com.enrollment.system.dto.CobrancaResponse;
import com.enrollment.system.enums.StatusCobranca;
import com.enrollment.system.repository.CobrancaRepository;
import com.enrollment.system.repository.MatriculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

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
        Cobranca cobranca = cobrancaRepository.findById(cobrancaId)
                .orElseThrow(() -> new RuntimeException("Cobrança não encontrada"));

        System.out.println("Notificação enviada para o aluno: " + cobranca.getMatricula().getAluno().getNome());
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

    public List<CobrancaResponse> buscarCobrancasPendentes() {
        List<Cobranca> cobrancasPendentes = cobrancaRepository.findByStatus(StatusCobranca.PENDENTE);
        return cobrancasPendentes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<CobrancaResponse> buscarCobrancasPagas() {
        List<Cobranca> cobrancasPagas = cobrancaRepository.findByStatus(StatusCobranca.PAGA);
        return cobrancasPagas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CobrancaResponse toResponse(Cobranca cobranca) {
        return new CobrancaResponse(
                cobranca.getMatricula().getId(),
                cobranca.getValor(),
                cobranca.getStatus(),
                cobranca.getDataGeracao()
        );
    }
}