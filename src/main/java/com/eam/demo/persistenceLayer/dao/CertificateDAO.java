package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.CertificateDTO;
import com.eam.demo.persistenceLayer.entity.CertificateEntity;
import com.eam.demo.persistenceLayer.mapper.CertificateMapper;
import com.eam.demo.persistenceLayer.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CertificateDAO {

    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;

    public CertificateDTO save(CertificateDTO certificateDTO) {
        CertificateEntity entity = certificateMapper.toEntity(certificateDTO);
        CertificateEntity savedEntity = certificateRepository.save(entity);
        return certificateMapper.toDTO(savedEntity);
    }

    public Optional<CertificateDTO> findById(Long id) {
        return certificateRepository.findById(id)
                .map(certificateMapper::toDTO);
    }

    public List<CertificateDTO> findAll() {
        List<CertificateEntity> entities = certificateRepository.findAll();
        return certificateMapper.toDTOList(entities);
    }

    public Optional<CertificateDTO> update(Long id, CertificateDTO certificateDTO) {
        return certificateRepository.findById(id)
                .map(existingEntity -> {
                    certificateMapper.updateEntityFromDTO(certificateDTO, existingEntity);
                    CertificateEntity updatedEntity = certificateRepository.save(existingEntity);
                    return certificateMapper.toDTO(updatedEntity);
                });
    }

    public boolean deleteById(Long id) {
        if (certificateRepository.existsById(id)) {
            certificateRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(Long id) {
        return certificateRepository.existsById(id);
    }

    public long count() {
        return certificateRepository.count();
    }
}
