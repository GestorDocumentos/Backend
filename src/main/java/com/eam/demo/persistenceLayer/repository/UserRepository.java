package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <UserEntity,Long> {


}
