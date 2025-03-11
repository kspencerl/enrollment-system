package com.enrollment.system.dto;

import com.enrollment.system.enums.StatusMatricula;
import java.time.LocalDateTime;

public class MatriculaResponse {
    private Long id;
    private StatusMatricula status;
    private LocalDateTime dataMatricula;

    public MatriculaResponse(Long id, StatusMatricula status, LocalDateTime dataMatricula) {
        this.id = id;
        this.status = status;
        this.dataMatricula = dataMatricula;
    }

    public Long getId() {
        return id;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }
}
