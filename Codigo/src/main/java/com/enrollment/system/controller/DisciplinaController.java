package com.enrollment.system.controller;

import com.enrollment.system.dto.DisciplinaResponse;
import com.enrollment.system.service.DisciplinaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@Tag(name = "Disciplinas", description = "Gerenciamento das disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private DisciplinaService disciplinaService;

    @Operation(summary = "Listar disciplinas abertas no semestre")
    @GetMapping("/abertas")
    public ResponseEntity<List<DisciplinaResponse>> obterDisciplinasAbertas() {
        return ResponseEntity.ok(disciplinaService.buscarDisciplinasAbertas());
    }
}

