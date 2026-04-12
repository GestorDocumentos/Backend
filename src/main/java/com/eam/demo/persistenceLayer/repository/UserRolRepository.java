package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.UserRolEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRolRepository extends JpaRepository<UserRolEntity, Long> {

    List<UserRolEntity> findByUser(UserEntity user);

    List<UserRolEntity> findByRol(RolEntity rol);

    Optional<UserRolEntity> findByUserAndRol(UserEntity user, RolEntity rol);

}
