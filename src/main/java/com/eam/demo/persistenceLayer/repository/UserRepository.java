package com.eam.demo.persistenceLayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eam.demo.persistenceLayer.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long > {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserId(long id);
    List<UserEntity> findByRol(Rol rol);

}
