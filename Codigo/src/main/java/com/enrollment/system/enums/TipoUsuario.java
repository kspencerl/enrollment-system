package com.enrollment.system.enums;

public enum TipoUsuario {
    SECRETARIA(0),
    ALUNO(1),
    PROFESSOR(2);

    private int tipo;

    TipoUsuario(int tipo) {
        this.tipo = tipo;
    }
}
