package com.eam.demo.controller;

import com.eam.demo.business.dto.CertificateDTO;
import com.eam.demo.persistenceLayer.dao.CertificateDAO;
import com.eam.demo.persistenceLayer.entity.TypeCertificateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CertificateController unit tests")
class CertificateControllerTest {

    @Mock
    private CertificateDAO certificateDAO;

    @InjectMocks
    private CertificateController certificateController;

    private CertificateDTO certificateDTO;

    @BeforeEach
    void setUp() {
        certificateDTO = new CertificateDTO(1L, 10L, TypeCertificateEntity.ESTUDIO, "firma", LocalDate.of(2026, 4, 19));
    }

    @Test
    void create_WithValidRequest_ShouldReturnOk() {
        when(certificateDAO.save(certificateDTO)).thenReturn(certificateDTO);

        ResponseEntity<CertificateDTO> response = certificateController.create(certificateDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(certificateDTO);
        verify(certificateDAO).save(certificateDTO);
    }

    @Test
    void getById_WhenExists_ShouldReturnOk() {
        when(certificateDAO.findById(1L)).thenReturn(Optional.of(certificateDTO));

        ResponseEntity<CertificateDTO> response = certificateController.getById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(certificateDTO);
    }

    @Test
    void getById_WhenMissing_ShouldReturnNotFound() {
        when(certificateDAO.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<CertificateDTO> response = certificateController.getById(99L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void getAll_ShouldReturnOk() {
        when(certificateDAO.findAll()).thenReturn(List.of(certificateDTO));

        ResponseEntity<List<CertificateDTO>> response = certificateController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(certificateDTO);
    }

    @Test
    void update_WhenExists_ShouldReturnOk() {
        when(certificateDAO.update(1L, certificateDTO)).thenReturn(Optional.of(certificateDTO));

        ResponseEntity<CertificateDTO> response = certificateController.update(1L, certificateDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(certificateDTO);
    }

    @Test
    void delete_WhenExists_ShouldReturnNoContent() {
        when(certificateDAO.deleteById(1L)).thenReturn(true);

        ResponseEntity<Void> response = certificateController.delete(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
