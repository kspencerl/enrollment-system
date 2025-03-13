package com.enrollment.system.models;

import com.enrollment.system.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@Table(name = "secretaria")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Secretaria extends Usuario {

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    public Secretaria() {
    }

    public Secretaria(Long id, String nome, String email, String senha, TipoUsuario tipo) {
        super(id, nome, email, senha, tipo);
    }

}
