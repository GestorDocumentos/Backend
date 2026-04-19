package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.UserSubjectsDTO;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.UserSubjectsEntity;
import com.eam.demo.persistenceLayer.mapper.UserSubjectsMapper;
import com.eam.demo.persistenceLayer.repository.UserSubjectsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserSubjectsDAO unit tests")
class UserSubjectsDAOTest {

    @Mock
    private UserSubjectsRepository userSubjectsRepository;

    @Mock
    private UserSubjectsMapper userSubjectsMapper;

    @InjectMocks
    private UserSubjectsDAO userSubjectsDAO;

    private UserSubjectsDTO userSubjectsDTO;
    private UserSubjectsEntity userSubjectsEntity;

    @BeforeEach
    void setUp() {
        userSubjectsDTO = new UserSubjectsDTO(1L, 20L, 30L, 4.5);
        userSubjectsEntity = new UserSubjectsEntity();
        userSubjectsEntity.setId(1L);
        userSubjectsEntity.setNota(4.5);
    }

    @Test
    void save_ShouldMapPersistAndReturnDto() {
        when(userSubjectsMapper.toEntity(userSubjectsDTO)).thenReturn(userSubjectsEntity);
        when(userSubjectsRepository.save(userSubjectsEntity)).thenReturn(userSubjectsEntity);
        when(userSubjectsMapper.toDTO(userSubjectsEntity)).thenReturn(userSubjectsDTO);

        UserSubjectsDTO result = userSubjectsDAO.save(userSubjectsDTO);

        assertThat(result).isEqualTo(userSubjectsDTO);
        verify(userSubjectsMapper).toEntity(userSubjectsDTO);
        verify(userSubjectsRepository).save(userSubjectsEntity);
    }

    @Test
    void findById_WhenExists_ShouldReturnDto() {
        when(userSubjectsRepository.findById(1L)).thenReturn(Optional.of(userSubjectsEntity));
        when(userSubjectsMapper.toDTO(userSubjectsEntity)).thenReturn(userSubjectsDTO);

        Optional<UserSubjectsDTO> result = userSubjectsDAO.findById(1L);

        assertThat(result).contains(userSubjectsDTO);
        verify(userSubjectsRepository).findById(1L);
    }

    @Test
    void findAll_ShouldReturnMappedList() {
        when(userSubjectsRepository.findAll()).thenReturn(List.of(userSubjectsEntity));
        when(userSubjectsMapper.toDTOList(List.of(userSubjectsEntity))).thenReturn(List.of(userSubjectsDTO));

        List<UserSubjectsDTO> result = userSubjectsDAO.findAll();

        assertThat(result).containsExactly(userSubjectsDTO);
        verify(userSubjectsRepository).findAll();
    }

    @Test
    void findByUserId_ShouldBuildUserAndReturnMappedList() {
        when(userSubjectsRepository.findByUser(any(UserEntity.class))).thenReturn(List.of(userSubjectsEntity));
        when(userSubjectsMapper.toDTOList(List.of(userSubjectsEntity))).thenReturn(List.of(userSubjectsDTO));

        List<UserSubjectsDTO> result = userSubjectsDAO.findByUserId(20L);

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userSubjectsRepository).findByUser(captor.capture());
        assertThat(captor.getValue().getIdUser()).isEqualTo(20L);
        assertThat(result).containsExactly(userSubjectsDTO);
    }

    @Test
    void update_WhenExists_ShouldPersistUpdatedEntity() {
        UserSubjectsDTO updateDTO = new UserSubjectsDTO(null, 20L, 30L, 5.0);
        when(userSubjectsRepository.findById(1L)).thenReturn(Optional.of(userSubjectsEntity));
        when(userSubjectsRepository.save(userSubjectsEntity)).thenReturn(userSubjectsEntity);
        when(userSubjectsMapper.toDTO(userSubjectsEntity)).thenReturn(userSubjectsDTO);

        Optional<UserSubjectsDTO> result = userSubjectsDAO.update(1L, updateDTO);

        assertThat(result).contains(userSubjectsDTO);
        verify(userSubjectsMapper).updateEntityFromDTO(updateDTO, userSubjectsEntity);
        verify(userSubjectsRepository).save(userSubjectsEntity);
    }

    @Test
    void deleteById_WhenExists_ShouldDeleteAndReturnTrue() {
        when(userSubjectsRepository.existsById(1L)).thenReturn(true);

        boolean result = userSubjectsDAO.deleteById(1L);

        assertThat(result).isTrue();
        verify(userSubjectsRepository).deleteById(1L);
    }
}
