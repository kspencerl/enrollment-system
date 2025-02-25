package com.enrollment.system.enums;

public enum StatusDisciplina {
    ATIVA(0),
    CANCELADA(1);

    private int status;

    StatusDisciplina(int status) {
        this.status = status;
    }
}
