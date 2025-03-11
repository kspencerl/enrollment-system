package com.enrollment.system.controller;

import com.enrollment.system.dto.*;
import com.enrollment.system.models.Disciplina;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.enrollment.system.models.Matricula;
import com.enrollment.system.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/aluno")
@Tag(name = "Aluno", description = "API para operações relacionadas à ações do aluno.")
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

    @Operation(summary = "Buscar Disciplinas Matriculadas", description = "Retorna a lista de disciplinas em que o aluno está matriculado no periodo.")
    @GetMapping("/{aluno}/disciplinas/{periodo}")
    public ResponseEntity<List<DisciplinaResponse>> consultarDisciplinasMatriculadas(
            @PathVariable Long aluno, @PathVariable Long periodo) {

        List<DisciplinaResponse> disciplinas = matriculaService.consultarDisciplinasMatriculadas(aluno, periodo);
        return ResponseEntity.ok(disciplinas);
    }

    @Operation(summary = "Remover disciplina da matrícula", description = "Remove uma disciplina da matrícula do aluno.")
    @DeleteMapping("/{matricula}/disciplinas/{disciplina}")
    public ResponseEntity<String> removerDisciplina(@PathVariable Long matricula, @PathVariable Long disciplina) {
        matriculaService.removerDisciplinaDaMatricula(matricula, disciplina);
        return ResponseEntity.ok("Disciplina removida com sucesso.");
    }

    @Operation(summary = "Cancelar matrícula", description = "Cancela a matrícula de um aluno com base nos dados fornecidos.")
    @PostMapping("/cancelar")
    public ResponseEntity<String> cancelarMatricula(@RequestBody CancelarMatriculaRequest request) {
        matriculaService.cancelarMatricula(request);
        return ResponseEntity.ok("Matrícula cancelada com sucesso.");
    }

    @Operation(summary = "Finalizar matrícula", description = "Finaliza a matrícula de um aluno com base nos dados fornecidos.")
    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarMatricula(@RequestBody FinalizarMatriculaRequest request) {
        matriculaService.finalizarMatricula(request);
        return ResponseEntity.ok("Matrícula finalizada com sucesso.");
    }
}
