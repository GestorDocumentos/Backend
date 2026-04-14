package com.eam.demo.business.service;

import com.eam.demo.business.dto.UserRolDTO;
import com.eam.demo.persistenceLayer.dao.UserRolDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRolService {

    private final UserRolDAO userRolDAO;

    public List<UserRolDTO> getAll() {
        return userRolDAO.findAll();
    }

    public Optional<UserRolDTO> getById(Long id) {
        return userRolDAO.findById(id);
    }

    public List<UserRolDTO> getByUser(Long userId) {
        return userRolDAO.findByUser(userId);
    }

    public List<UserRolDTO> getByRolType(String rolType) {
        return userRolDAO.findByRolType(rolType);
    }

    public Optional<UserRolDTO> assignRol(Long userId, Long rolId) {
        return userRolDAO.assignRol(userId, rolId);
    }

    public Optional<UserRolDTO> toggleSuspend(Long userRolId) {
        return userRolDAO.toggleSuspend(userRolId);
    }

    public boolean removeRol(Long id) {
        return userRolDAO.deleteById(id);
    }

    public long count() {
        return userRolDAO.count();
    }
}