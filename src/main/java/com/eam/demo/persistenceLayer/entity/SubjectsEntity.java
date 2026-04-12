package com.eam.demo.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subjects")
public class SubjectsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "subjects")
    private List<UserSubjectsEntity> users;

}
