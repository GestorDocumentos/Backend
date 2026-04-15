package com.eam.demo.persistenceLayer.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "certificate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateEntity {

    @Id
    private long idCertificate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "document_id")
    private DocumentEntity idDocument;

    @Enumerated(EnumType.STRING)
    private TypeCertificateEntity tipoCertificate;

    private String sign;

    private LocalDate fechaEmision;



}