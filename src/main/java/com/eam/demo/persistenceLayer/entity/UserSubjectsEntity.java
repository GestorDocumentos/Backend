package com.eam.demo.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_subjects")
@NoArgsConstructor
@AllArgsConstructor
public class UserSubjectsEntity {

    @Id
    private String id ;

    @OneToMany
    @JoinColumn(name = "id_user")
    private UserEntity User;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private SubjectsEntity subject;

    private double nota;

}
