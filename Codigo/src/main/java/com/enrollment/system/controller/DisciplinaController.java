package com.enrollment.system.controller;

import com.enrollment.system.dto.DisciplinaResponse;
import com.enrollment.system.service.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    private DisciplinaService disciplinaService;

    @GetMapping("/abertas")
    public ResponseEntity<List<DisciplinaResponse>> obterDisciplinasAbertas() {
        List<DisciplinaResponse> disciplinas = disciplinaService.buscarDisciplinasAbertas();
        return ResponseEntity.ok(disciplinas);
    }
}

