package com.enrollment.system.repository;

import com.enrollment.system.enums.StatusDisciplina;
import com.enrollment.system.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findByStatus(StatusDisciplina status);


}
