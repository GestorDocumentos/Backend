package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.DocumentEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DocumentRepository extends JpaRepository <DocumentEntity,Long> {
    List<DocumentEntity> findByUsuario(UserEntity usuario);
}
