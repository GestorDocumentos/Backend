package com.eam.demo.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información del usuario")
public class UserDTO {

    @Schema(description = "ID único del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idUser;

    @Schema(description = "Nombre completo del usuario", example = "Juan García")
    private String name;

    @Schema(description = "Correo electrónico del usuario", example = "juan.garcia@eam.com")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "Contrasena del usuario", example = "MiClaveSegura123", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    @Schema(description = "Roles asignados al usuario")
    private List<String> roles;

    @Schema(description = "Materias asociadas al usuario")
    private List<String> subjects;
}
