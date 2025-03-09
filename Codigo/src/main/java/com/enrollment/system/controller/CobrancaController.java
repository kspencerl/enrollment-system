package com.enrollment.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.enrollment.system.service.CobrancaService;
import com.enrollment.system.dto.CobrancaResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/cobrancas")
public class CobrancaController {
    private final CobrancaService cobrancaService;

    public CobrancaController(CobrancaService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<CobrancaResponse>> obterCobrancasPendentes() {
        List<CobrancaResponse> cobrancas = cobrancaService.buscarCobrancasPendentes();
        return ResponseEntity.ok(cobrancas);
    }

    @GetMapping("/pagas")
    public ResponseEntity<List<CobrancaResponse>> obterCobrancasPagas() {
        List<CobrancaResponse> cobrancas = cobrancaService.buscarCobrancasPagas();
        return ResponseEntity.ok(cobrancas);
    }
}