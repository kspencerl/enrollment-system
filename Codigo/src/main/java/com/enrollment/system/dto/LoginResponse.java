package com.enrollment.system.dto;

import com.enrollment.system.enums.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String message;
    private TipoUsuario tipoUsuario;

    public LoginResponse(String message, TipoUsuario tipoUsuario) {
        this.message = message;
        this.tipoUsuario = tipoUsuario;
    }
}
