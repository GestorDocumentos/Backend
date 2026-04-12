package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.DocumentEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository <DocumentEntity,Long> {
    List<DocumentEntity> findByUsuario(UserEntity usuario);
}
