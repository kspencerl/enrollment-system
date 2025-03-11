package com.enrollment.system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.enrollment.system.dto.CurriculoResponse;
import com.enrollment.system.dto.CursoResponse;
import com.enrollment.system.dto.DisciplinaResponse;
import com.enrollment.system.models.Curriculo;
import com.enrollment.system.repository.CurriculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculoService {

    private final CurriculoRepository curriculoRepository;

    public List<CurriculoResponse> obterCurriculosPorCurso(Long cursoId) {
        List<Curriculo> curriculos = curriculoRepository.findByCursoId(cursoId);

        if (curriculos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Currículos não encontrados para o curso informado.");
        }

        return curriculos.stream()
            .map(curriculo -> new CurriculoResponse(
                curriculo.getSemestre(),
                new CursoResponse(curriculo.getCurso().getNome(), curriculo.getCurso().getCreditos()),
                curriculo.getDisciplinas().stream()
                    .map(d -> new DisciplinaResponse(
                        d.getNome(),
                        d.getProfessor().getNome(),
                        d.getCredito(),
                        d.getValor(),
                        d.getCategoria()))
                    .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());
    }
    
}
