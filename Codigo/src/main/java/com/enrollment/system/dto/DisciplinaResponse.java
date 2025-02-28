package com.enrollment.system.dto;

import com.enrollment.system.enums.CategoriaDisciplina;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DisciplinaResponse {
    private String nome;
    private String professor;
    private int credito;
    private BigDecimal valor;
    private CategoriaDisciplina categoria;

    public DisciplinaResponse(String nome, String professor, int credito, BigDecimal valor, CategoriaDisciplina categoria) {
        this.nome = nome;
        this.professor = professor;
        this.credito = credito;
        this.valor = valor;
        this.categoria = categoria;
    }
}
