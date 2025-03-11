package com.enrollment.system.controller;

import com.enrollment.system.dto.MatriculaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.enrollment.system.dto.CancelarMatriculaRequest;
import com.enrollment.system.dto.MatriculaRequest;
import com.enrollment.system.models.Matricula;
import com.enrollment.system.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
@Tag(name = "Aluno API", description = "API para operações relacionadas à ações do aluno.")
public class AlunoController {

    @Autowired
    private MatriculaService matriculaService;

    @Operation(summary = "Efetuar matrícula", description = "Realiza a matrícula de um aluno com base nos dados fornecidos.")
    @PostMapping("/matricular")
    public ResponseEntity<MatriculaResponse> efetuarMatricula(@RequestBody MatriculaRequest request) {
        Matricula matricula = matriculaService.efetuarMatricula(request);
        MatriculaResponse response = new MatriculaResponse(matricula.getId(), matricula.getStatus(), matricula.getDataMatricula());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cancelar matrícula", description = "Cancela a matrícula de um aluno com base nos dados fornecidos.")
    @PostMapping("/cancelar")
    public ResponseEntity<String> cancelarMatricula(@RequestBody CancelarMatriculaRequest request) {
        matriculaService.cancelarMatricula(request);
        return ResponseEntity.ok("Matrícula cancelada com sucesso.");
    }
}
