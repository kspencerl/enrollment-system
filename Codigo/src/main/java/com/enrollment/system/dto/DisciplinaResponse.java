package com.enrollment.system.dto;

import com.enrollment.system.enums.CategoriaDisciplina;
import com.enrollment.system.enums.StatusDisciplina;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DisciplinaResponse {
    private String nome;
    private String professor;
    private int credito;
    private BigDecimal valor;
    private CategoriaDisciplina categoria;
    private StatusDisciplina status;
}
