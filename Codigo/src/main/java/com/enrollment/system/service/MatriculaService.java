package com.enrollment.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.enrollment.system.dto.CancelarMatriculaRequest;
import com.enrollment.system.dto.PeriodoMatriculaResponse;
import com.enrollment.system.enums.StatusMatricula;
import com.enrollment.system.dto.MatriculaRequest;

import org.springframework.stereotype.Service;
import com.enrollment.system.repository.*;
import lombok.RequiredArgsConstructor;
import com.enrollment.system.models.*;

import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private PeriodoMatriculaRepository periodoMatriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private MatriculaDisciplinaRepository matriculaDisciplinaRepository;

    public Matricula efetuarMatricula(MatriculaRequest request) {
        Aluno aluno = (Aluno) alunoRepository.findById(request.getAluno())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        PeriodoMatricula periodo = periodoMatriculaRepository.findById(request.getPeriodo())
                .orElseThrow(() -> new RuntimeException("Período de matrícula não encontrado"));

        LocalDate hoje = LocalDate.now();
        if (hoje.isBefore(periodo.getData_inicio()) || hoje.isAfter(periodo.getData_fim())) {
            throw new RuntimeException("O período de matrícula não está aberto.");
        }

        Matricula matricula = matriculaRepository
                .findByAlunoIdAndPeriodoMatriculaId(aluno.getId(), periodo.getId())
                .orElseGet(() -> {
                    Matricula novaMatricula = new Matricula();
                    novaMatricula.setAluno(aluno);
                    novaMatricula.setPeriodoMatricula(periodo);
                    novaMatricula.setStatus(StatusMatricula.INICIADA);
                    novaMatricula.setDataMatricula(LocalDateTime.now());
                    return matriculaRepository.save(novaMatricula);
                });

        List<Disciplina> disciplinas = disciplinaRepository.findAllById(request.getDisciplinas());

        if (disciplinas.isEmpty()) {
            throw new RuntimeException("Nenhuma disciplina válida foi selecionada.");
        }

        List<Long> disciplinasJaMatriculadas = matriculaDisciplinaRepository
                .findByMatriculaId(matricula.getId())
                .stream()
                .map(md -> md.getDisciplina().getId())
                .collect(Collectors.toList());

        List<MatriculaDisciplina> novasDisciplinas = disciplinas.stream()
                .filter(disciplina -> !disciplinasJaMatriculadas.contains(disciplina.getId()))
                .map(disciplina -> {
                    MatriculaDisciplina matriculaDisciplina = new MatriculaDisciplina();
                    matriculaDisciplina.setMatricula(matricula);
                    matriculaDisciplina.setDisciplina(disciplina);
                    return matriculaDisciplina;
                })
                .collect(Collectors.toList());

        if (!novasDisciplinas.isEmpty()) {
            matriculaDisciplinaRepository.saveAll(novasDisciplinas);
        }

        return matricula; //Preciso validar isso, para que nçao retorne Matricula e sim um response
    }

    public void cancelarMatricula(CancelarMatriculaRequest request) {
        Matricula matricula = matriculaRepository.findById(request.getMatricula())
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        matricula.setStatus(StatusMatricula.CANCELADA);
        matriculaRepository.save(matricula);
    }

    // isPeriodMatricula() : boolean
    public boolean isPeriodoMatriculaAberto(PeriodoMatriculaResponse periodo) {
        LocalDate hoje = LocalDate.now();
        return !hoje.isBefore(periodo.getDataInicio()) && !hoje.isAfter(periodo.getDataFim());
    }
}