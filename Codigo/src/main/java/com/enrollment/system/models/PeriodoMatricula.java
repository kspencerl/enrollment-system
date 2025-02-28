package com.enrollment.system.models;

import com.enrollment.system.enums.StatusPeriodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "periodo_matricula")
public class PeriodoMatricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataInicio")
    private LocalDate data_inicio;

    @Column(name = "dataFim")
    private LocalDate data_fim;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPeriodo status = StatusPeriodo.ABERTO;
}

