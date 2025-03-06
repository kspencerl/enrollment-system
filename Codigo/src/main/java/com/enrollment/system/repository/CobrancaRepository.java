package com.enrollment.system.repository;

import com.enrollment.system.models.Cobranca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {
    Optional<Cobranca> findById(Long cobrancaId);
}