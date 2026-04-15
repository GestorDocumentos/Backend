package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.UserDTO;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.mapper.UserMapper;
import com.eam.demo.persistenceLayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO save(UserDTO userDTO) {
        UserEntity entity = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(entity));
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTO);
    }

    public List<UserDTO> findAll() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    public Optional<UserDTO> update(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(existingEntity -> {
            userMapper.updateEntityFromDTO(userDTO, existingEntity);
            return userMapper.toDTO(userRepository.save(existingEntity));
        });
    }

    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(Long id) { return userRepository.existsById(id); }
    public long count() { return userRepository.count(); }
}