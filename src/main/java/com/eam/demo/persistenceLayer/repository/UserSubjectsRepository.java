package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.UserSubjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubjectsRepository extends JpaRepository <UserSubjectsEntity,Long> {

    List<UserSubjectsRepository> findByUser(UserEntity user);
}
