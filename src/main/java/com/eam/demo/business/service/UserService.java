package com.eam.demo.business.service;

import com.eam.demo.business.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();
    Optional<UserDTO> getById(Long id);
    Optional<UserDTO> getByEmail(String email);
    UserDTO create(UserDTO dto);
    Optional<UserDTO> update(Long id, UserDTO dto);
    boolean delete(Long id);
    long count();
}