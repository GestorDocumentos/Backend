package com.eam.demo.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private long idUser;

    private String name;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRolEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSubjectsEntity> subjects;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<DocumentEntity> documents;
}
