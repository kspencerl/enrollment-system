package com.enrollment.system.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodoMatriculaResponse {
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public PeriodoMatriculaResponse(LocalDate dataInicio, LocalDate dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}