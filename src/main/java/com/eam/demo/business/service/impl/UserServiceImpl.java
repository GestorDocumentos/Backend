package com.eam.demo.business.service.impl;

import com.eam.demo.business.dto.UserDTO;
import com.eam.demo.business.service.UserService;
import com.eam.demo.persistenceLayer.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public List<UserDTO> getAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<UserDTO> getById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public Optional<UserDTO> getByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        return userDAO.save(dto);
    }

    @Override
    public Optional<UserDTO> update(Long id, UserDTO dto) {
        return userDAO.update(id, dto);
    }

    @Override
    public boolean delete(Long id) {
        return userDAO.deleteById(id);
    }

    @Override
    public long count() {
        return userDAO.count();
    }
}