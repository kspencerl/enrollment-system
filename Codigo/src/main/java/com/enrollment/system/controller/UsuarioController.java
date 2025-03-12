package com.enrollment.system.controller;

import com.enrollment.system.dto.LoginRequest;
import com.enrollment.system.dto.LoginResponse;
import com.enrollment.system.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Autenticação usuário",
            description = "Realiza a autenticação do usuário utilizando email e senha"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso liberado"),
            @ApiResponse(responseCode = "401", description = "Acesso negado: Credenciais inválidas", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = usuarioService.autenticarUsuario(loginRequest.getEmail(), loginRequest.getSenha());
        return ResponseEntity.ok(response);
    }
}
