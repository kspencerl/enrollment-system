package com.enrollment.system.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurriculoResponse {
    private String semestre;
    private CursoResponse curso;
    private List<DisciplinaResponse> disciplinas;
}
