package com.enrollment.system.repository;

import com.enrollment.system.models.Cobranca;
import com.enrollment.system.enums.StatusCobranca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {
    List<Cobranca> findByStatus(StatusCobranca status);
}