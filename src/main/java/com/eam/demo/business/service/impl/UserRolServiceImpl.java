package com.eam.demo.business.service.impl;

import com.eam.demo.business.dto.UserRolDTO;
import com.eam.demo.business.service.UserRolService;
import com.eam.demo.persistenceLayer.dao.UserRolDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRolServiceImpl implements UserRolService {

    private final UserRolDAO userRolDAO;

    @Override
    public List<UserRolDTO> getAll() {
        return userRolDAO.findAll();
    }

    @Override
    public Optional<UserRolDTO> getById(Long id) {
        return userRolDAO.findById(id);
    }

    @Override
    public List<UserRolDTO> getByUser(Long userId) {
        return userRolDAO.findByUser(userId);
    }

    @Override
    public List<UserRolDTO> getByRolType(String rolType) {
        return userRolDAO.findByRolType(rolType);
    }

    @Override
    public Optional<UserRolDTO> assignRol(Long userId, Long rolId) {
        return userRolDAO.assignRol(userId, rolId);
    }

    @Override
    public Optional<UserRolDTO> toggleSuspend(Long userRolId) {
        return userRolDAO.toggleSuspend(userRolId);
    }

    @Override
    public boolean removeRol(Long id) {
        return userRolDAO.deleteById(id);
    }

    @Override
    public long count() {
        return userRolDAO.count();
    }
}