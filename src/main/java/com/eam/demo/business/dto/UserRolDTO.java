package com.eam.demo.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Asignación de rol a un usuario")
public class UserRolDTO {

    @Schema(description = "ID único de la asignación", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idUserRol;

    @Schema(description = "ID del usuario", example = "1")
    private Long userId;

    @Schema(description = "Nombre del usuario", example = "Juan García")
    private String userName;

    @Schema(description = "Rol asignado", example = "STUDENT")
    private String rol;

    @Schema(description = "Estado de suspensión", example = "false")
    private boolean suspended;
}