package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.SubjectsDTO;
import com.eam.demo.persistenceLayer.entity.SubjectsEntity;
import com.eam.demo.persistenceLayer.mapper.SubjectsMapper;
import com.eam.demo.persistenceLayer.repository.SubjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubjectsDAO {

    private final SubjectsRepository subjectsRepository;
    private final SubjectsMapper subjectsMapper;

    public SubjectsDTO save(SubjectsDTO subjectsDTO) {
        SubjectsEntity entity = subjectsMapper.toEntity(subjectsDTO);
        SubjectsEntity savedEntity = subjectsRepository.save(entity);
        return subjectsMapper.toDTO(savedEntity);
    }

    public Optional<SubjectsDTO> findById(Long id) {
        return subjectsRepository.findById(id)
                .map(subjectsMapper::toDTO);
    }

    public List<SubjectsDTO> findAll() {
        List<SubjectsEntity> entities = subjectsRepository.findAll();
        return subjectsMapper.toDTOList(entities);
    }

    public Optional<SubjectsDTO> update(Long id, SubjectsDTO subjectsDTO) {
        return subjectsRepository.findById(id)
                .map(existingEntity -> {
                    subjectsMapper.updateEntityFromDTO(subjectsDTO, existingEntity);
                    SubjectsEntity updatedEntity = subjectsRepository.save(existingEntity);
                    return subjectsMapper.toDTO(updatedEntity);
                });
    }

    public boolean deleteById(Long id) {
        if (subjectsRepository.existsById(id)) {
            subjectsRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
