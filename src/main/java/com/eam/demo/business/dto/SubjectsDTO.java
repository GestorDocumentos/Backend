package com.eam.demo.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion de una asignatura")
public class SubjectsDTO {

    @Schema(description = "ID unico de la asignatura", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre de la asignatura", example = "Matematicas", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 100)
    private String name;
}
