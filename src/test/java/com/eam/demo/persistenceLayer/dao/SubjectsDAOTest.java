package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.SubjectsDTO;
import com.eam.demo.persistenceLayer.entity.SubjectsEntity;
import com.eam.demo.persistenceLayer.mapper.SubjectsMapper;
import com.eam.demo.persistenceLayer.repository.SubjectsRepository;
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
@DisplayName("SubjectsDAO unit tests")
class SubjectsDAOTest {

    @Mock
    private SubjectsRepository subjectsRepository;

    @Mock
    private SubjectsMapper subjectsMapper;

    @InjectMocks
    private SubjectsDAO subjectsDAO;

    private SubjectsDTO subjectsDTO;
    private SubjectsEntity subjectsEntity;

    @BeforeEach
    void setUp() {
        subjectsDTO = new SubjectsDTO(1L, "Matematicas");
        subjectsEntity = new SubjectsEntity();
        subjectsEntity.setId(1L);
        subjectsEntity.setName("Matematicas");
    }

    @Test
    void save_ShouldMapPersistAndReturnDto() {
        when(subjectsMapper.toEntity(subjectsDTO)).thenReturn(subjectsEntity);
        when(subjectsRepository.save(subjectsEntity)).thenReturn(subjectsEntity);
        when(subjectsMapper.toDTO(subjectsEntity)).thenReturn(subjectsDTO);

        SubjectsDTO result = subjectsDAO.save(subjectsDTO);

        assertThat(result).isEqualTo(subjectsDTO);
        verify(subjectsMapper).toEntity(subjectsDTO);
        verify(subjectsRepository).save(subjectsEntity);
    }

    @Test
    void findById_WhenExists_ShouldReturnDto() {
        when(subjectsRepository.findById(1L)).thenReturn(Optional.of(subjectsEntity));
        when(subjectsMapper.toDTO(subjectsEntity)).thenReturn(subjectsDTO);

        Optional<SubjectsDTO> result = subjectsDAO.findById(1L);

        assertThat(result).contains(subjectsDTO);
        verify(subjectsRepository).findById(1L);
    }

    @Test
    void findAll_ShouldReturnMappedList() {
        when(subjectsRepository.findAll()).thenReturn(List.of(subjectsEntity));
        when(subjectsMapper.toDTOList(List.of(subjectsEntity))).thenReturn(List.of(subjectsDTO));

        List<SubjectsDTO> result = subjectsDAO.findAll();

        assertThat(result).containsExactly(subjectsDTO);
        verify(subjectsRepository).findAll();
    }

    @Test
    void update_WhenExists_ShouldPersistUpdatedEntity() {
        SubjectsDTO updateDTO = new SubjectsDTO(null, "Historia");
        when(subjectsRepository.findById(1L)).thenReturn(Optional.of(subjectsEntity));
        when(subjectsRepository.save(subjectsEntity)).thenReturn(subjectsEntity);
        when(subjectsMapper.toDTO(subjectsEntity)).thenReturn(subjectsDTO);

        Optional<SubjectsDTO> result = subjectsDAO.update(1L, updateDTO);

        assertThat(result).contains(subjectsDTO);
        verify(subjectsMapper).updateEntityFromDTO(updateDTO, subjectsEntity);
        verify(subjectsRepository).save(subjectsEntity);
    }

    @Test
    void deleteById_WhenExists_ShouldDeleteAndReturnTrue() {
        when(subjectsRepository.existsById(1L)).thenReturn(true);

        boolean result = subjectsDAO.deleteById(1L);

        assertThat(result).isTrue();
        verify(subjectsRepository).deleteById(1L);
    }
}
