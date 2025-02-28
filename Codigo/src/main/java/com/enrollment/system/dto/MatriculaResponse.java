package com.enrollment.system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaResponse {
    private String message;
    private Long matriculaId;

    public MatriculaResponse(String mensagem, Long matriculaId) {
        this.message = mensagem;
        this.matriculaId = matriculaId;
    }
}

