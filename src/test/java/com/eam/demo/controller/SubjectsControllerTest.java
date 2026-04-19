package com.eam.demo.controller;

import com.eam.demo.business.dto.SubjectsDTO;
import com.eam.demo.persistenceLayer.dao.SubjectsDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("SubjectsController unit tests")
class SubjectsControllerTest {

    @Mock
    private SubjectsDAO subjectsDAO;

    @InjectMocks
    private SubjectsController subjectsController;

    private SubjectsDTO subjectsDTO;

    @BeforeEach
    void setUp() {
        subjectsDTO = new SubjectsDTO(1L, "Matematicas");
    }

    @Test
    void create_WithValidRequest_ShouldReturnOk() {
        when(subjectsDAO.save(subjectsDTO)).thenReturn(subjectsDTO);

        ResponseEntity<SubjectsDTO> response = subjectsController.create(subjectsDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(subjectsDTO);
    }

    @Test
    void getById_WhenExists_ShouldReturnOk() {
        when(subjectsDAO.findById(1L)).thenReturn(Optional.of(subjectsDTO));

        ResponseEntity<SubjectsDTO> response = subjectsController.getById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(subjectsDTO);
    }

    @Test
    void getById_WhenMissing_ShouldReturnNotFound() {
        when(subjectsDAO.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<SubjectsDTO> response = subjectsController.getById(99L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getAll_ShouldReturnOk() {
        when(subjectsDAO.findAll()).thenReturn(List.of(subjectsDTO));

        ResponseEntity<List<SubjectsDTO>> response = subjectsController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(subjectsDTO);
    }

    @Test
    void update_WhenExists_ShouldReturnOk() {
        when(subjectsDAO.update(1L, subjectsDTO)).thenReturn(Optional.of(subjectsDTO));

        ResponseEntity<SubjectsDTO> response = subjectsController.update(1L, subjectsDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(subjectsDTO);
    }

    @Test
    void delete_WhenMissing_ShouldReturnNotFound() {
        when(subjectsDAO.deleteById(99L)).thenReturn(false);

        ResponseEntity<Void> response = subjectsController.delete(99L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
