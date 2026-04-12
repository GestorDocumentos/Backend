package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository <CertificateEntity, Long> {


}
