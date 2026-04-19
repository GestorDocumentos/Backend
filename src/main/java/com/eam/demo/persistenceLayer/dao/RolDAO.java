package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.RolDTO;
import com.eam.demo.persistenceLayer.entity.RolEntity;
import com.eam.demo.persistenceLayer.mapper.RolMapper;
import com.eam.demo.persistenceLayer.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RolDAO {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public RolDTO save(RolDTO rolDTO) {
        return rolMapper.toDTO(rolRepository.save(rolMapper.toEntity(rolDTO)));
    }

    public Optional<RolDTO> findById(Long id) {
        return rolRepository.findById(id).map(rolMapper::toDTO);
    }

    public Optional<RolDTO> findByRolType(String rolType) {
        try {
            RolEntity.RolType type = RolEntity.RolType.valueOf(rolType);
            return rolRepository.findByRolType(type).map(rolMapper::toDTO);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public List<RolDTO> findAll() {
        return rolMapper.toDTOList(rolRepository.findAll());
    }

    public Optional<RolDTO> update(Long id, RolDTO rolDTO) {
        return rolRepository.findById(id).map(existing -> {
            existing.setRolType(RolEntity.RolType.valueOf(rolDTO.getRolType()));
            return rolMapper.toDTO(rolRepository.save(existing));
        });
    }

    public boolean deleteById(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(Long id) { return rolRepository.existsById(id); }
    public long count() { return rolRepository.count(); }
}