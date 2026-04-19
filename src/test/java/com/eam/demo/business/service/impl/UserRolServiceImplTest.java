package com.eam.demo.business.service.impl;

import com.eam.demo.business.dto.UserRolDTO;
import com.eam.demo.persistenceLayer.dao.UserRolDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserRolServiceImpl unit tests")
class UserRolServiceImplTest {

    @Mock
    private UserRolDAO userRolDAO;

    @InjectMocks
    private UserRolServiceImpl userRolService;

    private UserRolDTO userRolDTO;

    @BeforeEach
    void setUp() {
        userRolDTO = new UserRolDTO(1L, 10L, "Juan Perez", "STUDENT", false);
    }

    @Test
    void getAll_ShouldReturnAssignmentsFromDao() {
        List<UserRolDTO> expected = List.of(userRolDTO);
        when(userRolDAO.findAll()).thenReturn(expected);

        List<UserRolDTO> result = userRolService.getAll();

        assertThat(result).isEqualTo(expected);
        verify(userRolDAO).findAll();
    }

    @Test
    void getById_ShouldReturnOptionalFromDao() {
        when(userRolDAO.findById(1L)).thenReturn(Optional.of(userRolDTO));

        Optional<UserRolDTO> result = userRolService.getById(1L);

        assertThat(result).contains(userRolDTO);
        verify(userRolDAO).findById(1L);
    }

    @Test
    void getByUser_ShouldReturnAssignmentsFromDao() {
        List<UserRolDTO> expected = List.of(userRolDTO);
        when(userRolDAO.findByUser(10L)).thenReturn(expected);

        List<UserRolDTO> result = userRolService.getByUser(10L);

        assertThat(result).isEqualTo(expected);
        verify(userRolDAO).findByUser(10L);
    }

    @Test
    void getByRolType_ShouldReturnAssignmentsFromDao() {
        List<UserRolDTO> expected = List.of(userRolDTO);
        when(userRolDAO.findByRolType("STUDENT")).thenReturn(expected);

        List<UserRolDTO> result = userRolService.getByRolType("STUDENT");

        assertThat(result).isEqualTo(expected);
        verify(userRolDAO).findByRolType("STUDENT");
    }

    @Test
    void assignRol_ShouldReturnAssignmentFromDao() {
        when(userRolDAO.assignRol(10L, 2L)).thenReturn(Optional.of(userRolDTO));

        Optional<UserRolDTO> result = userRolService.assignRol(10L, 2L);

        assertThat(result).contains(userRolDTO);
        verify(userRolDAO).assignRol(10L, 2L);
    }

    @Test
    void toggleSuspend_ShouldReturnUpdatedAssignment() {
        UserRolDTO suspended = new UserRolDTO(1L, 10L, "Juan Perez", "STUDENT", true);
        when(userRolDAO.toggleSuspend(1L)).thenReturn(Optional.of(suspended));

        Optional<UserRolDTO> result = userRolService.toggleSuspend(1L);

        assertThat(result).contains(suspended);
        verify(userRolDAO).toggleSuspend(1L);
    }

    @Test
    void removeRol_ShouldReturnDaoResult() {
        when(userRolDAO.deleteById(1L)).thenReturn(true);

        boolean result = userRolService.removeRol(1L);

        assertThat(result).isTrue();
        verify(userRolDAO).deleteById(1L);
    }

    @Test
    void count_ShouldReturnDaoCount() {
        when(userRolDAO.count()).thenReturn(5L);

        long result = userRolService.count();

        assertThat(result).isEqualTo(5L);
        verify(userRolDAO).count();
    }
}
