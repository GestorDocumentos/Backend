package com.eam.demo.business.service.impl;

import com.eam.demo.business.dto.RolDTO;
import com.eam.demo.persistenceLayer.dao.RolDAO;
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
@DisplayName("RolServiceImpl unit tests")
class RolServiceImplTest {

    @Mock
    private RolDAO rolDAO;

    @InjectMocks
    private RolServiceImpl rolService;

    private RolDTO rolDTO;

    @BeforeEach
    void setUp() {
        rolDTO = new RolDTO(1L, "STUDENT");
    }

    @Test
    void getAll_ShouldReturnRolesFromDao() {
        List<RolDTO> expected = List.of(rolDTO);
        when(rolDAO.findAll()).thenReturn(expected);

        List<RolDTO> result = rolService.getAll();

        assertThat(result).isEqualTo(expected);
        verify(rolDAO).findAll();
    }

    @Test
    void getById_ShouldReturnOptionalFromDao() {
        when(rolDAO.findById(1L)).thenReturn(Optional.of(rolDTO));

        Optional<RolDTO> result = rolService.getById(1L);

        assertThat(result).contains(rolDTO);
        verify(rolDAO).findById(1L);
    }

    @Test
    void getByRolType_ShouldReturnOptionalFromDao() {
        when(rolDAO.findByRolType("STUDENT")).thenReturn(Optional.of(rolDTO));

        Optional<RolDTO> result = rolService.getByRolType("STUDENT");

        assertThat(result).contains(rolDTO);
        verify(rolDAO).findByRolType("STUDENT");
    }

    @Test
    void create_ShouldDelegateToDao() {
        when(rolDAO.save(rolDTO)).thenReturn(rolDTO);

        RolDTO result = rolService.create(rolDTO);

        assertThat(result).isEqualTo(rolDTO);
        verify(rolDAO).save(rolDTO);
    }

    @Test
    void update_ShouldDelegateToDao() {
        when(rolDAO.update(1L, rolDTO)).thenReturn(Optional.of(rolDTO));

        Optional<RolDTO> result = rolService.update(1L, rolDTO);

        assertThat(result).contains(rolDTO);
        verify(rolDAO).update(1L, rolDTO);
    }

    @Test
    void delete_ShouldReturnDaoResult() {
        when(rolDAO.deleteById(1L)).thenReturn(true);

        boolean result = rolService.delete(1L);

        assertThat(result).isTrue();
        verify(rolDAO).deleteById(1L);
    }

    @Test
    void count_ShouldReturnDaoCount() {
        when(rolDAO.count()).thenReturn(4L);

        long result = rolService.count();

        assertThat(result).isEqualTo(4L);
        verify(rolDAO).count();
    }
}
