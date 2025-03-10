package com.enrollment.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.enrollment.system.models.PeriodoMatricula;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoMatriculaRepository extends JpaRepository<PeriodoMatricula, Long> {
}