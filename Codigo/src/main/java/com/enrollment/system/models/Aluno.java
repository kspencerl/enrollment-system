package com.enrollment.system.models;

import com.enrollment.system.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="aluno")
@PrimaryKeyJoinColumn(name = "id_usuario")
@DiscriminatorValue("aluno")
public class Aluno extends Usuario {

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
}


