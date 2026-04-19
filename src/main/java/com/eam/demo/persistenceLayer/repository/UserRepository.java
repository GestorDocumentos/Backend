package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

}
