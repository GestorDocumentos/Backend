package com.eam.demo.business.service;

import com.eam.demo.business.dto.UserRolDTO;
import java.util.List;
import java.util.Optional;

public interface UserRolService {
    List<UserRolDTO> getAll();
    Optional<UserRolDTO> getById(Long id);
    List<UserRolDTO> getByUser(Long userId);
    List<UserRolDTO> getByRolType(String rolType);
    Optional<UserRolDTO> assignRol(Long userId, Long rolId);
    Optional<UserRolDTO> toggleSuspend(Long userRolId);
    boolean removeRol(Long id);
    long count();
}