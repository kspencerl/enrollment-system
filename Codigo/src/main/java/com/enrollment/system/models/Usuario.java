package com.enrollment.system.models;

import com.enrollment.system.enums.TipoUsuario;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class Usuario {

    private String nome;

    private String email;

    private String senha;

    private TipoUsuario tipoUsuario;

}
