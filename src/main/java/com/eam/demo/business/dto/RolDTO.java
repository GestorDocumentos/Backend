package com.eam.demo.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información del rol")
public class RolDTO {

    @Schema(description = "ID único del rol", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idRol;

    @Schema(description = "Tipo de rol asignado", example = "STUDENT")
    private String rolType;
}