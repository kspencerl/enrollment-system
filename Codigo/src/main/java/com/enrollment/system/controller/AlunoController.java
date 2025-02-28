package com.enrollment.system.controller;

import com.enrollment.system.dto.CancelarMatriculaRequest;
import com.enrollment.system.dto.MatriculaRequest;
import com.enrollment.system.models.Matricula;
import com.enrollment.system.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private MatriculaService matriculaService;

    @PostMapping("/matricular")
    public ResponseEntity<Matricula> efetuarMatricula(@RequestBody MatriculaRequest request) {
        Matricula matricula = matriculaService.efetuarMatricula(request);
        return ResponseEntity.ok(matricula);
    }

    @PostMapping("/cancelar")
    public ResponseEntity<String> cancelarMatricula(@RequestBody CancelarMatriculaRequest request) {
        matriculaService.cancelarMatricula(request);
        return ResponseEntity.ok("Matrícula cancelada com sucesso.");
    }
}
