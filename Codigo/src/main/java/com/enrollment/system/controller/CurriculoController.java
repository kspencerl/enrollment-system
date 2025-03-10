package com.enrollment.system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enrollment.system.dto.CurriculoResponse;
import com.enrollment.system.service.CurriculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/curriculos")
@Tag(name = "Currículos", description = "Gerenciamento dos currículos")
@RequiredArgsConstructor
public class CurriculoController {

    private final CurriculoService curriculoService;

    @Operation(summary = "Obter currículos de um curso")
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<CurriculoResponse>> obterCurriculosPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(curriculoService.obterCurriculosPorCurso(cursoId));
    }
}
