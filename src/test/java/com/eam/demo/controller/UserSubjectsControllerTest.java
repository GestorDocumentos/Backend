package com.eam.demo.controller;

import com.eam.demo.business.dto.UserSubjectsDTO;
import com.eam.demo.persistenceLayer.dao.UserSubjectsDAO;
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
@DisplayName("UserSubjectsController unit tests")
class UserSubjectsControllerTest {

    @Mock
    private UserSubjectsDAO userSubjectsDAO;

    @InjectMocks
    private UserSubjectsController userSubjectsController;

    private UserSubjectsDTO userSubjectsDTO;

    @BeforeEach
    void setUp() {
        userSubjectsDTO = new UserSubjectsDTO(1L, 7L, 3L, 4.2);
    }

    @Test
    void create_WithValidRequest_ShouldReturnOk() {
        when(userSubjectsDAO.save(userSubjectsDTO)).thenReturn(userSubjectsDTO);

        ResponseEntity<UserSubjectsDTO> response = userSubjectsController.create(userSubjectsDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(userSubjectsDTO);
    }

    @Test
    void getById_WhenExists_ShouldReturnOk() {
        when(userSubjectsDAO.findById(1L)).thenReturn(Optional.of(userSubjectsDTO));

        ResponseEntity<UserSubjectsDTO> response = userSubjectsController.getById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(userSubjectsDTO);
    }

    @Test
    void getAll_ShouldReturnOk() {
        when(userSubjectsDAO.findAll()).thenReturn(List.of(userSubjectsDTO));

        ResponseEntity<List<UserSubjectsDTO>> response = userSubjectsController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(userSubjectsDTO);
    }

    @Test
    void getByUserId_ShouldReturnOk() {
        when(userSubjectsDAO.findByUserId(7L)).thenReturn(List.of(userSubjectsDTO));

        ResponseEntity<List<UserSubjectsDTO>> response = userSubjectsController.getByUserId(7L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(userSubjectsDTO);
    }

    @Test
    void update_WhenMissing_ShouldReturnNotFound() {
        when(userSubjectsDAO.update(88L, userSubjectsDTO)).thenReturn(Optional.empty());

        ResponseEntity<UserSubjectsDTO> response = userSubjectsController.update(88L, userSubjectsDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void delete_WhenExists_ShouldReturnNoContent() {
        when(userSubjectsDAO.deleteById(1L)).thenReturn(true);

        ResponseEntity<Void> response = userSubjectsController.delete(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
