package com.enrollment.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enrollment.system.models.Curriculo;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
    List<Curriculo> findByCursoId(Long cursoId);
    
}
