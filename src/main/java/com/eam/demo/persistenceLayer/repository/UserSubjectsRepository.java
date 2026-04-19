package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.UserSubjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserSubjectsRepository extends JpaRepository <UserSubjectsEntity,Long> {

    List<UserSubjectsEntity> findByUser(UserEntity user);
}
