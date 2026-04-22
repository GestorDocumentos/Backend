package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.SubjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<SubjectsEntity, Long> {
    boolean existsByName(String name);
}
