package com.enrollment.system.service;

import com.enrollment.system.dto.DisciplinaResponse;
import com.enrollment.system.enums.StatusDisciplina;
import com.enrollment.system.models.Disciplina;
import com.enrollment.system.repository.DisciplinaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;

    public List<DisciplinaResponse> buscarDisciplinasAbertas() {
        List<Disciplina> disciplinasAbertas = disciplinaRepository.findByStatus(StatusDisciplina.ATIVA);

        if (disciplinasAbertas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há disciplinas em aberto no momento");
        }

        return disciplinasAbertas.stream()
                .map(d -> new DisciplinaResponse(
                        d.getNome(),
                        d.getProfessor().getNome(),
                        d.getCredito(),
                        d.getValor(),
                        d.getCategoria(),
                        d.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
