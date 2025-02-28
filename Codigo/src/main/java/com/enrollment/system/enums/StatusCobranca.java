package com.enrollment.system.enums;

public enum StatusCobranca {
    PENDENTE(1),
    PAGA(2);

    private int status;
    StatusCobranca(int status) {
        this.status = status;
    }
}
