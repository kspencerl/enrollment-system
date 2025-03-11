package com.enrollment.system.repository;

import com.enrollment.system.models.Disciplina;
import com.enrollment.system.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Optional<Matricula> findByAlunoIdAndPeriodoMatriculaId(Long alunoId, Long periodoMatriculaId);

    @Query(value = "SELECT d.* FROM matricula m " +
            "JOIN matricula_disciplina md ON m.id = md.id_matricula " +
            "JOIN disciplina d ON md.id_disciplina = d.id " +
            "WHERE m.id_aluno = :idAluno AND m.id_periodo = :idPeriodo",
            nativeQuery = true)
    List<Disciplina> buscarDisciplinasPorAlunoEPeriodo(@Param("idAluno") Long idAluno,
                                                       @Param("idPeriodo") Long idPeriodo);
}

