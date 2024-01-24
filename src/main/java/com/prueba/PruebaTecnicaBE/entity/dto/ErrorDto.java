package com.prueba.PruebaTecnicaBE.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private String message;

}
