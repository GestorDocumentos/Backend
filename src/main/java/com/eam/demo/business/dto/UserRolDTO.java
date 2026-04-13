package com.eam.demo.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Relacion entre usuario y rol")
public class UserRolDTO {

    @Schema(description = "ID unico del registro de relacion", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idUserRol;

    @Schema(description = "ID del usuario relacionado", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(description = "ID del rol relacionado", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long rolId;

    @Schema(description = "Indica si el usuario esta suspendido en este rol", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean suspended;
}
