package com.enrollment.system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CancelarMatriculaRequest {
    private Long matricula;
}