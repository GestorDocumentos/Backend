package com.eam.demo.business.dto;

import com.eam.demo.persistenceLayer.entity.DocumentStatusEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion del documento")
public class DocumentDTO {

    @Schema(description = "ID unico del documento", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Tipo del documento", example = "PDF", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 100)
    private String tipo;

    @Schema(description = "Fecha del documento", example = "2026-04-12", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fecha;

    @Schema(description = "Estado del documento", example = "Pendiente", requiredMode = Schema.RequiredMode.REQUIRED)
    private DocumentStatusEntity estado;

    @Schema(description = "ID del usuario asociado al documento", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long usuarioId;
}
