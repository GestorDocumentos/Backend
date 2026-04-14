package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.UserRolDTO;
import com.eam.demo.persistenceLayer.entity.RolEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.UserRolEntity;
import com.eam.demo.persistenceLayer.mapper.UserRolMapper;
import com.eam.demo.persistenceLayer.repository.RolRepository;
import com.eam.demo.persistenceLayer.repository.UserRepository;
import com.eam.demo.persistenceLayer.repository.UserRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRolDAO {

    private final UserRolRepository userRolRepository;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserRolMapper userRolMapper;

    /**
     * CREATE - Asignar rol a usuario
     *
     * FLUJO:
     * 1. Buscar UserEntity y RolEntity por sus IDs
     * 2. Verificar que no exista ya la asignación
     * 3. Crear nueva UserRolEntity y guardar
     * 4. Retornar DTO con la asignación creada
     */
    public Optional<UserRolDTO> assignRol(Long userId, Long rolId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<RolEntity> rol = rolRepository.findById(rolId);

        if (user.isEmpty() || rol.isEmpty()) return Optional.empty();

        // Evita duplicados
        Optional<UserRolEntity> existing = userRolRepository.findByUserAndRol(user.get(), rol.get());
        if (existing.isPresent()) return Optional.of(userRolMapper.toDTO(existing.get()));

        UserRolEntity newAssignment = new UserRolEntity(null, user.get(), rol.get(), false);
        return Optional.of(userRolMapper.toDTO(userRolRepository.save(newAssignment)));
    }

    /**
     * READ - Buscar asignación por ID
     */
    public Optional<UserRolDTO> findById(Long id) {
        return userRolRepository.findById(id)
                .map(userRolMapper::toDTO);
    }

    /**
     * READ - Buscar todas las asignaciones de un usuario
     */
    public List<UserRolDTO> findByUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> userRolMapper.toDTOList(userRolRepository.findByUser(user)))
                .orElse(List.of());
    }

    /**
     * READ - Buscar todas las asignaciones de un rol
     */
    public List<UserRolDTO> findByRolType(String rolType) {
        try {
            RolEntity.RolType type = RolEntity.RolType.valueOf(rolType);
            return rolRepository.findByRolType(type)
                    .map(rol -> userRolMapper.toDTOList(userRolRepository.findByRol(rol)))
                    .orElse(List.of());
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    /**
     * READ ALL - Buscar todas las asignaciones
     */
    public List<UserRolDTO> findAll() {
        return userRolMapper.toDTOList(userRolRepository.findAll());
    }

    /**
     * UPDATE - Cambiar estado de suspensión
     *
     * NOTA: Invierte el estado actual de suspended
     */
    public Optional<UserRolDTO> toggleSuspend(Long userRolId) {
        return userRolRepository.findById(userRolId)
                .map(entity -> {
                    entity.setSuspended(!entity.isSuspended());
                    return userRolMapper.toDTO(userRolRepository.save(entity));
                });
    }

    /**
     * DELETE - Eliminar asignación por ID
     *
     * @return boolean - true si se eliminó, false si no existía
     */
    public boolean deleteById(Long id) {
        if (userRolRepository.existsById(id)) {
            userRolRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * UTILIDAD - Verificar si existe asignación por ID
     */
    public boolean existsById(Long id) {
        return userRolRepository.existsById(id);
    }

    /**
     * UTILIDAD - Contar total de asignaciones
     */
    public long count() {
        return userRolRepository.count();
    }
}