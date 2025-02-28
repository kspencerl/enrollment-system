package com.enrollment.system.enums;

public enum StatusMatricula {
    INICIADA(1),
    EM_ANDAMENTO(2),
    FINALIZADA(3),
    CANCELADA(4);

    private int status;
    StatusMatricula(int status) {
        this.status = status;
    }
}
