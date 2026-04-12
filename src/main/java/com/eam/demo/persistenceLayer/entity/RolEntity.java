package com.eam.demo.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RolType rolType;

    public enum RolType {
        TEACHER, STUDENT, ADMIN, EMPLOYEE
    }
}
