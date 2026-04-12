package com.eam.demo.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserRol;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolEntity rol;

    @Column(nullable = false)
    private boolean suspended = false; // true = usuario suspendido en este rol
}
