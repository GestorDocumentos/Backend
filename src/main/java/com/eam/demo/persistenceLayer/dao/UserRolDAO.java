package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.UserRolDTO;
import com.eam.demo.persistenceLayer.entity.*;
import com.eam.demo.persistenceLayer.mapper.UserRolMapper;
import com.eam.demo.persistenceLayer.repository.*;
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

    public Optional<UserRolDTO> assignRol(Long userId, Long rolId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<RolEntity> rol = rolRepository.findById(rolId);
        if (user.isEmpty() || rol.isEmpty()) return Optional.empty();
        Optional<UserRolEntity> existing = userRolRepository.findByUserAndRol(user.get(), rol.get());
        if (existing.isPresent()) return Optional.of(userRolMapper.toDTO(existing.get()));
        UserRolEntity newAssignment = new UserRolEntity(null, user.get(), rol.get(), false);
        return Optional.of(userRolMapper.toDTO(userRolRepository.save(newAssignment)));
    }

    public Optional<UserRolDTO> findById(Long id) {
        return userRolRepository.findById(id).map(userRolMapper::toDTO);
    }

    public List<UserRolDTO> findByUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> userRolMapper.toDTOList(userRolRepository.findByUser(user)))
                .orElse(List.of());
    }

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

    public List<UserRolDTO> findAll() {
        return userRolMapper.toDTOList(userRolRepository.findAll());
    }

    public Optional<UserRolDTO> toggleSuspend(Long userRolId) {
        return userRolRepository.findById(userRolId).map(entity -> {
            entity.setSuspended(!entity.isSuspended());
            return userRolMapper.toDTO(userRolRepository.save(entity));
        });
    }

    public boolean deleteById(Long id) {
        if (userRolRepository.existsById(id)) {
            userRolRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(Long id) {
        return userRolRepository.existsById(id);
    }

    public long count() {
        return userRolRepository.count();
    }
}