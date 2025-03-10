package com.enrollment.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enrollment.system.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {}