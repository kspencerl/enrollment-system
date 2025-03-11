package com.enrollment.system.repository;

import com.enrollment.system.models.Disciplina;
import com.enrollment.system.models.MatriculaDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaDisciplinaRepository extends JpaRepository<MatriculaDisciplina, Long> {
    List<MatriculaDisciplina> findByMatriculaId(Long matriculaId);
}
