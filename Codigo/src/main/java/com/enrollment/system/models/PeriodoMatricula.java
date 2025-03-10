package com.enrollment.system.models;

import com.enrollment.system.enums.StatusPeriodo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
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