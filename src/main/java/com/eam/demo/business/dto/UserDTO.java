package com.eam.demo.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion del usuario")
public class UserDTO {

    @Schema(description = "ID unico del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idUser;

    @Schema(description = "Nombre del usuario", example = "Juan Perez", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 100)
    private String name;

    @Schema(description = "Correo electronico del usuario", example = "juan.perez@eam.com", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 150)
    private String email;
}
