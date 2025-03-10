package com.enrollment.system.service;

import com.enrollment.system.repository.PeriodoMatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.enrollment.system.dto.PeriodoMatriculaResponse;
import com.enrollment.system.models.PeriodoMatricula;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class PeriodoMatriculaService {

    @Autowired
    private PeriodoMatriculaRepository periodoMatriculaRepository;

    public List<PeriodoMatriculaResponse> buscarPeriodosAbertos() {
        List<PeriodoMatricula> periodos = periodoMatriculaRepository.findAll();

        return periodos.stream()
                .map(periodo -> new PeriodoMatriculaResponse(
                        periodo.getData_inicio(),
                        periodo.getData_fim()
                ))
                .filter(periodoResponse -> periodoResponse.getDataInicio().isBefore(java.time.LocalDate.now()) &&
                        periodoResponse.getDataFim().isAfter(java.time.LocalDate.now()))
                .collect(Collectors.toList());
    }
}