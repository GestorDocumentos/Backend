package com.eam.demo.business.service;

import com.eam.demo.business.dto.RolDTO;
import java.util.List;
import java.util.Optional;

public interface RolService {
    List<RolDTO> getAll();
    Optional<RolDTO> getById(Long id);
    Optional<RolDTO> getByRolType(String rolType);
    RolDTO create(RolDTO dto);
    Optional<RolDTO> update(Long id, RolDTO dto);
    boolean delete(Long id);
    long count();
}