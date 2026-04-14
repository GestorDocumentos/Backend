package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.DocumentDTO;
import com.eam.demo.persistenceLayer.entity.DocumentEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.mapper.DocumentMapper;
import com.eam.demo.persistenceLayer.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DocumentDAO {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentDTO save(DocumentDTO documentDTO) {
        DocumentEntity entity = documentMapper.toEntity(documentDTO);
        DocumentEntity savedEntity = documentRepository.save(entity);
        return documentMapper.toDTO(savedEntity);
    }

    public Optional<DocumentDTO> findById(Long id) {
        return documentRepository.findById(id)
                .map(documentMapper::toDTO);
    }

    public List<DocumentDTO> findAll() {
        List<DocumentEntity> entities = documentRepository.findAll();
        return documentMapper.toDTOList(entities);
    }

    public List<DocumentDTO> findByUsuarioId(Long userId) {
        return documentMapper.toDTOList(documentRepository.findByUsuario(toUserEntity(userId)));
    }

    public Optional<DocumentDTO> update(Long id, DocumentDTO documentDTO) {
        return documentRepository.findById(id)
                .map(existingEntity -> {
                    documentMapper.updateEntityFromDTO(documentDTO, existingEntity);
                    DocumentEntity updatedEntity = documentRepository.save(existingEntity);
                    return documentMapper.toDTO(updatedEntity);
                });
    }

    public boolean deleteById(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(Long id) {
        return documentRepository.existsById(id);
    }

    public long count() {
        return documentRepository.count();
    }

    private UserEntity toUserEntity(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setIdUser(userId);
        return user;
    }
}
