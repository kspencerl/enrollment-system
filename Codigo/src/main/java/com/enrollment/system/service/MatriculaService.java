package com.enrollment.system.service;

import com.enrollment.system.dto.CancelarMatriculaRequest;
import com.enrollment.system.dto.MatriculaRequest;
import com.enrollment.system.enums.StatusMatricula;
import com.enrollment.system.models.*;
import com.enrollment.system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
}

