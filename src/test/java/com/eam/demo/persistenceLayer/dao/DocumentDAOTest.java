package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.DocumentDTO;
import com.eam.demo.persistenceLayer.entity.DocumentEntity;
import com.eam.demo.persistenceLayer.entity.DocumentStatusEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.mapper.DocumentMapper;
import com.eam.demo.persistenceLayer.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DocumentDAO unit tests")
class DocumentDAOTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @InjectMocks
    private DocumentDAO documentDAO;

    private DocumentDTO documentDTO;
    private DocumentEntity documentEntity;

    @BeforeEach
    void setUp() {
        documentDTO = new DocumentDTO(1L, "PDF", LocalDate.of(2026, 4, 18), DocumentStatusEntity.EMITIDO, 20L);
        documentEntity = new DocumentEntity();
        documentEntity.setId(1L);
        documentEntity.setTipo("PDF");
        documentEntity.setFecha(LocalDate.of(2026, 4, 18));
        documentEntity.setEstado(DocumentStatusEntity.EMITIDO);
    }

    @Test
    void save_ShouldMapPersistAndReturnDto() {
        when(documentMapper.toEntity(documentDTO)).thenReturn(documentEntity);
        when(documentRepository.save(documentEntity)).thenReturn(documentEntity);
        when(documentMapper.toDTO(documentEntity)).thenReturn(documentDTO);

        DocumentDTO result = documentDAO.save(documentDTO);

        assertThat(result).isEqualTo(documentDTO);
        verify(documentMapper).toEntity(documentDTO);
        verify(documentRepository).save(documentEntity);
        verify(documentMapper).toDTO(documentEntity);
    }

    @Test
    void findById_WhenExists_ShouldReturnDto() {
        when(documentRepository.findById(1L)).thenReturn(Optional.of(documentEntity));
        when(documentMapper.toDTO(documentEntity)).thenReturn(documentDTO);

        Optional<DocumentDTO> result = documentDAO.findById(1L);

        assertThat(result).contains(documentDTO);
        verify(documentRepository).findById(1L);
    }

    @Test
    void findAll_ShouldReturnMappedList() {
        when(documentRepository.findAll()).thenReturn(List.of(documentEntity));
        when(documentMapper.toDTOList(List.of(documentEntity))).thenReturn(List.of(documentDTO));

        List<DocumentDTO> result = documentDAO.findAll();

        assertThat(result).containsExactly(documentDTO);
        verify(documentRepository).findAll();
    }

    @Test
    void findByUsuarioId_ShouldBuildUserAndReturnMappedList() {
        when(documentRepository.findByUsuario(org.mockito.ArgumentMatchers.any(UserEntity.class))).thenReturn(List.of(documentEntity));
        when(documentMapper.toDTOList(List.of(documentEntity))).thenReturn(List.of(documentDTO));

        List<DocumentDTO> result = documentDAO.findByUsuarioId(20L);

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(documentRepository).findByUsuario(captor.capture());
        assertThat(captor.getValue().getIdUser()).isEqualTo(20L);
        assertThat(result).containsExactly(documentDTO);
    }

    @Test
    void update_WhenExists_ShouldPersistUpdatedEntity() {
        DocumentDTO updateDTO = new DocumentDTO(null, "DOCX", LocalDate.of(2026, 4, 19), DocumentStatusEntity.PENDIENTE, 20L);
        when(documentRepository.findById(1L)).thenReturn(Optional.of(documentEntity));
        when(documentRepository.save(documentEntity)).thenReturn(documentEntity);
        when(documentMapper.toDTO(documentEntity)).thenReturn(documentDTO);

        Optional<DocumentDTO> result = documentDAO.update(1L, updateDTO);

        assertThat(result).contains(documentDTO);
        verify(documentMapper).updateEntityFromDTO(updateDTO, documentEntity);
        verify(documentRepository).save(documentEntity);
    }

    @Test
    void deleteById_WhenExists_ShouldDeleteAndReturnTrue() {
        when(documentRepository.existsById(1L)).thenReturn(true);

        boolean result = documentDAO.deleteById(1L);

        assertThat(result).isTrue();
        verify(documentRepository).deleteById(1L);
    }

    @Test
    void existsById_ShouldDelegateToRepository() {
        when(documentRepository.existsById(1L)).thenReturn(true);

        boolean result = documentDAO.existsById(1L);

        assertThat(result).isTrue();
        verify(documentRepository).existsById(1L);
    }

    @Test
    void count_ShouldDelegateToRepository() {
        when(documentRepository.count()).thenReturn(8L);

        long result = documentDAO.count();

        assertThat(result).isEqualTo(8L);
        verify(documentRepository).count();
    }
}
