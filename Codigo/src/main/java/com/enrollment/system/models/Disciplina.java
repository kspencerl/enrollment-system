package com.enrollment.system.models;

import com.enrollment.system.enums.CategoriaDisciplina;
import com.enrollment.system.enums.StatusDisciplina;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private int credito;

    private int capacidadeMaxima;

    private int capacidadeMinima;

    private StatusDisciplina statusDisciplina;

    private CategoriaDisciplina categoriaDisciplina;

}
