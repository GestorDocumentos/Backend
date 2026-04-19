package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.CertificateDTO;
import com.eam.demo.persistenceLayer.entity.CertificateEntity;
import com.eam.demo.persistenceLayer.entity.TypeCertificateEntity;
import com.eam.demo.persistenceLayer.mapper.CertificateMapper;
import com.eam.demo.persistenceLayer.repository.CertificateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CertificateDAO unit tests")
class CertificateDAOTest {

    @Mock
    private CertificateRepository certificateRepository;

    @Mock
    private CertificateMapper certificateMapper;

    @InjectMocks
    private CertificateDAO certificateDAO;

    private CertificateDTO certificateDTO;
    private CertificateEntity certificateEntity;

    @BeforeEach
    void setUp() {
        certificateDTO = new CertificateDTO(1L, 10L, TypeCertificateEntity.ESTUDIO, "firma-001", LocalDate.of(2026, 4, 18));
        certificateEntity = new CertificateEntity();
        certificateEntity.setIdCertificate(1L);
        certificateEntity.setTipoCertificate(TypeCertificateEntity.ESTUDIO);
        certificateEntity.setSign("firma-001");
        certificateEntity.setFechaEmision(LocalDate.of(2026, 4, 18));
    }

    @Test
    void save_ShouldMapPersistAndReturnDto() {
        when(certificateMapper.toEntity(certificateDTO)).thenReturn(certificateEntity);
        when(certificateRepository.save(certificateEntity)).thenReturn(certificateEntity);
        when(certificateMapper.toDTO(certificateEntity)).thenReturn(certificateDTO);

        CertificateDTO result = certificateDAO.save(certificateDTO);

        assertThat(result).isEqualTo(certificateDTO);
        verify(certificateMapper).toEntity(certificateDTO);
        verify(certificateRepository).save(certificateEntity);
        verify(certificateMapper).toDTO(certificateEntity);
    }

    @Test
    void findById_WhenExists_ShouldReturnDto() {
        when(certificateRepository.findById(1L)).thenReturn(Optional.of(certificateEntity));
        when(certificateMapper.toDTO(certificateEntity)).thenReturn(certificateDTO);

        Optional<CertificateDTO> result = certificateDAO.findById(1L);

        assertThat(result).contains(certificateDTO);
        verify(certificateRepository).findById(1L);
    }

    @Test
    void findAll_ShouldReturnMappedList() {
        when(certificateRepository.findAll()).thenReturn(List.of(certificateEntity));
        when(certificateMapper.toDTOList(List.of(certificateEntity))).thenReturn(List.of(certificateDTO));

        List<CertificateDTO> result = certificateDAO.findAll();

        assertThat(result).containsExactly(certificateDTO);
        verify(certificateRepository).findAll();
    }

    @Test
    void update_WhenExists_ShouldPersistUpdatedEntity() {
        CertificateDTO updateDTO = new CertificateDTO(null, 10L, TypeCertificateEntity.MATERIAS, "firma-002", LocalDate.of(2026, 4, 19));

        when(certificateRepository.findById(1L)).thenReturn(Optional.of(certificateEntity));
        when(certificateRepository.save(certificateEntity)).thenReturn(certificateEntity);
        when(certificateMapper.toDTO(certificateEntity)).thenReturn(certificateDTO);

        Optional<CertificateDTO> result = certificateDAO.update(1L, updateDTO);

        assertThat(result).contains(certificateDTO);
        verify(certificateMapper).updateEntityFromDTO(updateDTO, certificateEntity);
        verify(certificateRepository).save(certificateEntity);
    }

    @Test
    void deleteById_WhenExists_ShouldDeleteAndReturnTrue() {
        when(certificateRepository.existsById(1L)).thenReturn(true);

        boolean result = certificateDAO.deleteById(1L);

        assertThat(result).isTrue();
        verify(certificateRepository).deleteById(1L);
    }

    @Test
    void existsById_ShouldDelegateToRepository() {
        when(certificateRepository.existsById(1L)).thenReturn(true);

        boolean result = certificateDAO.existsById(1L);

        assertThat(result).isTrue();
        verify(certificateRepository).existsById(1L);
    }

    @Test
    void count_ShouldDelegateToRepository() {
        when(certificateRepository.count()).thenReturn(7L);

        long result = certificateDAO.count();

        assertThat(result).isEqualTo(7L);
        verify(certificateRepository).count();
    }
}
