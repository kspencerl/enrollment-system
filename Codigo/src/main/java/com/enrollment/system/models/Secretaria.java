package com.enrollment.system.models;

import com.enrollment.system.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="secretaria")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Secretaria extends Usuario {
    public Secretaria(Long id, String nome, String email, String senha) {
        super(id, nome, email, senha, TipoUsuario.SECRETARIA);
    }
}

