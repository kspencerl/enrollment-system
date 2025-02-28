package com.enrollment.system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="curriculo")
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "semestre")
    private String semestre;

    @OneToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany
    @JoinColumn(name = "disciplina_id")
    private List<Disciplina> disciplina;

}
