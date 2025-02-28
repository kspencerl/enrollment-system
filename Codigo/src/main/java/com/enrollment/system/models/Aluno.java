package com.enrollment.system.models;

import com.enrollment.system.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="aluno")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Aluno extends Usuario {

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    public Aluno(Long id, String nome, String email, String senha, Curso curso) {
        super(id, nome, email, senha, TipoUsuario.ALUNO);
        this.curso = curso;
    }
}


