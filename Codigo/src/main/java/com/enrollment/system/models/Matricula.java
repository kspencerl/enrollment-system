package com.enrollment.system.models;

import com.enrollment.system.enums.StatusMatricula;
import com.enrollment.system.enums.StatusPeriodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matricula", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_aluno", "id_periodo"})
})
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoMatricula periodoMatricula;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusMatricula status = StatusMatricula.INICIADA;

    private LocalDateTime dataMatricula;

    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatriculaDisciplina> disciplinas = new ArrayList<>();
}


