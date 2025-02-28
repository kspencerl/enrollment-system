package com.enrollment.system.models;

import com.enrollment.system.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="professor")
@PrimaryKeyJoinColumn(name = "id_usuario")
@DiscriminatorValue("professor")
public class Professor extends Usuario {
    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplinas;
}

