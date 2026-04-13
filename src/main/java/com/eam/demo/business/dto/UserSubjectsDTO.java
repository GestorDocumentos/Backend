package com.eam.demo.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Relacion entre usuario y asignatura")
public class UserSubjectsDTO {

    @Schema(description = "ID unico del registro de relacion", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID del usuario relacionado", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(description = "ID de la asignatura relacionada", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long subjectId;

    @Schema(description = "Nota del usuario en la asignatura", example = "4.5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double nota;
}
