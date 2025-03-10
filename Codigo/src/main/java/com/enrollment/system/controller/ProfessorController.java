package com.enrollment.system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enrollment.system.dto.ProfessorResponse;
import com.enrollment.system.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/professores")
@Tag(name = "Professores", description = "Gerenciamento dos professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @Operation(summary = "Listar todos os professores")
    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listarProfessores() {
        return ResponseEntity.ok(professorService.listarProfessores());
    }
}