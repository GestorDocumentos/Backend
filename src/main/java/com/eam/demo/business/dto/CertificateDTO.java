package com.eam.demo.business.dto;

import com.eam.demo.persistenceLayer.entity.TypeCertificateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Informacion del certificado")
public class CertificateDTO {

    @Schema(description = "ID unico del certificado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idCertificate;

    @Schema(description = "ID del documento asociado al certificado", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long documentId;

    @Schema(description = "Tipo de certificado", example = "LABORAL", requiredMode = Schema.RequiredMode.REQUIRED)
    private TypeCertificateEntity tipoCertificate;

    @Schema(description = "Firma o identificador de firma del certificado", example = "firma-digital-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sign;

    @Schema(description = "Fecha de emision del certificado", example = "2026-04-12", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaEmision;
}
