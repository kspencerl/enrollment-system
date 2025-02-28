package com.enrollment.system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class MatriculaRequest {
    private Long aluno;
    private List<Long> disciplinas;
    private Long periodo;
}
