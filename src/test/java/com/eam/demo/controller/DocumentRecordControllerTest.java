package com.eam.demo.controller;

import com.eam.demo.business.dto.DocumentDTO;
import com.eam.demo.persistenceLayer.dao.DocumentDAO;
import com.eam.demo.persistenceLayer.entity.DocumentStatusEntity;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DocumentRecordController unit tests")
class DocumentRecordControllerTest {

    @Mock
    private DocumentDAO documentDAO;

    @InjectMocks
    private DocumentRecordController documentRecordController;

    private DocumentDTO documentDTO;

    @BeforeEach
    void setUp() {
        documentDTO = new DocumentDTO(1L, "PDF", LocalDate.of(2026, 4, 19), DocumentStatusEntity.EMITIDO, 5L);
    }

    @Test
    void create_WithValidRequest_ShouldReturnOk() {
        when(documentDAO.save(documentDTO)).thenReturn(documentDTO);

        ResponseEntity<DocumentDTO> response = documentRecordController.create(documentDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(documentDTO);
    }

    @Test
    void getById_WhenExists_ShouldReturnOk() {
        when(documentDAO.findById(1L)).thenReturn(Optional.of(documentDTO));

        ResponseEntity<DocumentDTO> response = documentRecordController.getById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(documentDTO);
    }

    @Test
    void getAll_ShouldReturnOk() {
        when(documentDAO.findAll()).thenReturn(List.of(documentDTO));

        ResponseEntity<List<DocumentDTO>> response = documentRecordController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(documentDTO);
    }

    @Test
    void getByUserId_ShouldReturnOk() {
        when(documentDAO.findByUsuarioId(5L)).thenReturn(List.of(documentDTO));

        ResponseEntity<List<DocumentDTO>> response = documentRecordController.getByUserId(5L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(documentDTO);
    }

    @Test
    void update_WhenMissing_ShouldReturnNotFound() {
        when(documentDAO.update(44L, documentDTO)).thenReturn(Optional.empty());

        ResponseEntity<DocumentDTO> response = documentRecordController.update(44L, documentDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void delete_WhenExists_ShouldReturnNoContent() {
        when(documentDAO.deleteById(1L)).thenReturn(true);

        ResponseEntity<Void> response = documentRecordController.delete(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
