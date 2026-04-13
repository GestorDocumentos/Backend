package com.eam.demo.business.dto;

import com.eam.demo.persistenceLayer.entity.RolEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion del rol")
public class RolDTO {

    @Schema(description = "ID unico del rol", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idRol;

    @Schema(description = "Tipo de rol del sistema", example = "Maestro", requiredMode = Schema.RequiredMode.REQUIRED)
    private RolEntity.RolType rolType;
}
