package com.enrollment.system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.enrollment.system.dto.CursoResponse;
import com.enrollment.system.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    public List<CursoResponse> listarCursos() {
        return cursoRepository.findAll().stream()
            .map(c -> new CursoResponse(c.getNome(), c.getCreditos()))
            .collect(Collectors.toList());
    }
}
