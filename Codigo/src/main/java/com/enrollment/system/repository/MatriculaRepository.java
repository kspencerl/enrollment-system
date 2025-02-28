package com.enrollment.system.repository;

import com.enrollment.system.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    boolean existsByAlunoIdAndPeriodoMatriculaId(Long alunoId, Long periodoId);
    Optional<Matricula> findByAlunoIdAndPeriodoMatriculaId(Long alunoId, Long periodoMatriculaId);
}

