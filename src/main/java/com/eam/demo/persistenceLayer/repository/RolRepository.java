package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.RolEntity;
import com.eam.demo.persistenceLayer.entity.RolEntity.RolType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<RolEntity, Long> {

    Optional<RolEntity> findByRolType(RolType rolType);

}
