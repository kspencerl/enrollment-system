package com.enrollment.system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.enrollment.system.dto.ProfessorResponse;
import com.enrollment.system.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    
    private final ProfessorRepository professorRepository;

    public List<ProfessorResponse> listarProfessores() {
        return professorRepository.findAll().stream()
            .map(p -> new ProfessorResponse(p.getNome(), p.getEmail()))
            .collect(Collectors.toList());
    }
}
