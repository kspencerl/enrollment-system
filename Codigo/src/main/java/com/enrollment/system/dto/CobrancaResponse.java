package com.enrollment.system.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.enrollment.system.enums.StatusCobranca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CobrancaResponse {
    private Long matriculaId;
    private BigDecimal valor;
    private StatusCobranca status;
    private LocalDateTime dataGeracao;

    public CobrancaResponse(Long matriculaId, BigDecimal valor, StatusCobranca status, LocalDateTime dataGeracao) {
        this.matriculaId = matriculaId;
        this.valor = valor;
        this.status = status;
        this.dataGeracao = dataGeracao;
    }
}