package com.enrollment.system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RemoverDisciplinaRequest {
    private Long idMatricula;
    private Long idDisciplina;
}
