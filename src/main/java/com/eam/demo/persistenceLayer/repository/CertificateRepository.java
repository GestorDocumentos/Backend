package com.eam.demo.persistenceLayer.repository;

import com.eam.demo.persistenceLayer.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository <CertificateEntity, Long> {


}
