package com.enrollment.system.enums;

public enum StatusPeriodo {
    ABERTO(1),
    FECHADO(2),
    ENCERRADO(3);

    private int status;
    StatusPeriodo(int status) {
        this.status = status;
    }
}
