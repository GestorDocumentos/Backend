package com.eam.demo.persistenceLayer.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "certificates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateEntity {

    @Id
    private long idCertificate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_certificate")
    private DocumentEntity idDocument;

    private String tipoCertificate;

    private String sign;

    private LocalDate fechaEmision;



}