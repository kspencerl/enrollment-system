package com.enrollment.system.models;

import com.enrollment.system.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="professor")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Professor extends Usuario {

    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplinas;

    public Professor(Long id, String nome, String email, String senha) {
        super(id, nome, email, senha, TipoUsuario.PROFESSOR);
    }
}

