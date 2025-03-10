package com.enrollment.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enrollment.system.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    
}
