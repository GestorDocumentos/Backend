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

    /**
     * CREATE - Crear nuevo rol
     *
     * FLUJO:
     * 1. RolDTO -> RolEntity (mapper toEntity())
     * 2. Guardar Entity en BD
     * 3. Entity guardada -> RolDTO (con ID generado)
     */
    public RolDTO save(RolDTO rolDTO) {
        RolEntity entity = rolMapper.toEntity(rolDTO);
        RolEntity savedEntity = rolRepository.save(entity);
        return rolMapper.toDTO(savedEntity);
    }

    /**
     * READ - Buscar rol por ID
     *
     * @return Optional<RolDTO> - empty si no existe
     */
    public Optional<RolDTO> findById(Long id) {
        return rolRepository.findById(id)
                .map(rolMapper::toDTO);
    }

    /**
     * READ - Buscar rol por tipo
     *
     * @return Optional<RolDTO> - empty si no existe
     */
    public Optional<RolDTO> findByRolType(String rolType) {
        try {
            RolEntity.RolType type = RolEntity.RolType.valueOf(rolType);
            return rolRepository.findByRolType(type)
                    .map(rolMapper::toDTO);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    /**
     * READ ALL - Buscar todos los roles
     */
    public List<RolDTO> findAll() {
        return rolMapper.toDTOList(rolRepository.findAll());
    }

    /**
     * UPDATE - Actualizar rol existente
     *
     * NOTA: Solo se puede cambiar el rolType
     */
    public Optional<RolDTO> update(Long id, RolDTO rolDTO) {
        return rolRepository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setRolType(RolEntity.RolType.valueOf(rolDTO.getRolType()));
                    RolEntity updatedEntity = rolRepository.save(existingEntity);
                    return rolMapper.toDTO(updatedEntity);
                });
    }

    /**
     * DELETE - Eliminar rol por ID
     *
     * @return boolean - true si se eliminó, false si no existía
     */
    public boolean deleteById(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * UTILIDAD - Verificar si existe rol por ID
     */
    public boolean existsById(Long id) {
        return rolRepository.existsById(id);
    }

    /**
     * UTILIDAD - Contar total de roles
     */
    public long count() {
        return rolRepository.count();
    }
}