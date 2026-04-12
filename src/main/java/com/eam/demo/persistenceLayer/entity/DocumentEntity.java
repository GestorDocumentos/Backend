package com.eam.demo.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDate;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tipo;
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoDocumento estado;

    @ManyToOne
    @JoinColumn(name= "id_usuario")
    private UserEntity usuario;

    @OneToOne(mappedBy = "documento", cascade = CascadeType.ALL)
    private CertificateEntity certificate;

}
