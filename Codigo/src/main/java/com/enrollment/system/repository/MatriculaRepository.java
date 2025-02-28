package com.enrollment.system.repository;

import com.enrollment.system.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Optional<Matricula> findByAlunoIdAndPeriodoMatriculaId(Long alunoId, Long periodoMatriculaId);
}

