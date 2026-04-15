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
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private SubjectsEntity subjects;

    private double nota;

}
