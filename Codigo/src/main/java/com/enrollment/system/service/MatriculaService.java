package com.enrollment.system.service;

import com.enrollment.system.dto.*;
import com.enrollment.system.enums.StatusCobranca;
import com.enrollment.system.enums.StatusMatricula;
import com.enrollment.system.models.*;
import com.enrollment.system.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private CobrancaRepository cobrancaRepository;

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

        List<Long> disciplinasJaMatriculadas = matriculaDisciplinaRepository
                .findByMatriculaId(matricula.getId())
                .stream()
                .map(md -> md.getDisciplina().getId())
                .collect(Collectors.toList());

        List<MatriculaDisciplina> novasDisciplinas = new ArrayList<>();

        for (Disciplina disciplina : disciplinas) {
            if (!disciplinasJaMatriculadas.contains(disciplina.getId())) {
                MatriculaDisciplina matriculaDisciplina = new MatriculaDisciplina();
                matriculaDisciplina.setMatricula(matricula);
                matriculaDisciplina.setDisciplina(disciplina);
                novasDisciplinas.add(matriculaDisciplina);

                disciplina.setQuantidadeAlunos(disciplina.getQuantidadeAlunos() + 1);
            }
        }

        if (!novasDisciplinas.isEmpty()) {
            matriculaDisciplinaRepository.saveAll(novasDisciplinas);
            disciplinaRepository.saveAll(disciplinas);
        }

        return matricula;
    }

    public List<DisciplinaResponse> consultarDisciplinasMatriculadas(Long idAluno, Long idPeriodo) {
        List<Disciplina> disciplinas = matriculaRepository.buscarDisciplinasPorAlunoEPeriodo(idAluno, idPeriodo);

        return disciplinas.stream()
                .map(d -> new DisciplinaResponse(
                        d.getNome(),
                        d.getProfessor().getNome(),
                        d.getCredito(),
                        d.getValor(),
                        d.getCategoria(),
                        d.getStatus()))
                .toList();
    }

    public void removerDisciplinaDaMatricula(Long idMatricula, Long idDisciplina) {
        MatriculaDisciplina matriculaDisciplina = matriculaDisciplinaRepository
                .findByMatricula_IdAndDisciplina_Id(idMatricula, idDisciplina)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada na matrícula!"));

        Disciplina disciplina = matriculaDisciplina.getDisciplina();

        if (disciplina.getQuantidadeAlunos() > 0) {
            disciplina.setQuantidadeAlunos(disciplina.getQuantidadeAlunos() - 1);
            disciplinaRepository.save(disciplina);
        }

        matriculaDisciplinaRepository.delete(matriculaDisciplina);
    }


    @Transactional
    public void finalizarMatricula(FinalizarMatriculaRequest request) {
        Matricula matricula = matriculaRepository.findById(request.getMatricula())
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        matricula.setStatus(StatusMatricula.FINALIZADA);
        matriculaRepository.save(matricula);

        List<MatriculaDisciplina> disciplinas = matriculaDisciplinaRepository.findByMatriculaId(matricula.getId());

        if (disciplinas.isEmpty()) {
            throw new RuntimeException("Nenhuma disciplina encontrada para essa matrícula!");
        }

        BigDecimal valorTotal = disciplinas.stream()
                .map(md -> md.getDisciplina().getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Cobranca> cobrancas = new ArrayList<>();
        LocalDate dataVencimento = LocalDate.now().plusMonths(1).withDayOfMonth(8);

        for (int i = 0; i < 6; i++) {
            Cobranca cobranca = new Cobranca();
            cobranca.setMatricula(matricula);
            cobranca.setValor(valorTotal);
            cobranca.setStatus(StatusCobranca.PENDENTE);
            cobranca.setDataGeracao(LocalDateTime.now());
            cobranca.setDataVencimento(dataVencimento);

            cobrancas.add(cobranca);

            dataVencimento = dataVencimento.plusMonths(1);
        }

        cobrancaRepository.saveAll(cobrancas);
    }

    @Transactional
    public void cancelarMatricula(CancelarMatriculaRequest request) {
        Matricula matricula = matriculaRepository.findById(request.getMatricula())
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        List<MatriculaDisciplina> matriculaDisciplinas = matriculaDisciplinaRepository.findByMatriculaId(request.getMatricula());

        for (MatriculaDisciplina matriculaDisciplina : matriculaDisciplinas) {

            Disciplina disciplina = matriculaDisciplina.getDisciplina();

            if (disciplina.getQuantidadeAlunos() > 0) {
                disciplina.setQuantidadeAlunos(disciplina.getQuantidadeAlunos() - 1);
                disciplinaRepository.save(disciplina);
            }
        }

        matricula.setStatus(StatusMatricula.CANCELADA);
        matriculaRepository.save(matricula);
    }

}

