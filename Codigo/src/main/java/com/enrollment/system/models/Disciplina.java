package com.enrollment.system.models;

import com.enrollment.system.enums.CategoriaDisciplina;
import com.enrollment.system.enums.StatusDisciplina;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Professor professor;

    private int credito;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusDisciplina status;

    @Enumerated(EnumType.STRING)
    private CategoriaDisciplina categoria;

    @Column(name = "quantidadeAlunos", nullable = false)
    private int quantidadeAlunos = 0;
}

