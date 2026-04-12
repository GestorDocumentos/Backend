package com.eam.demo.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    private String name;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<UserSubjectsEntity> subject;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<DocumentEntity> document;









}
