package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.UserSubjectsDTO;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.UserSubjectsEntity;
import com.eam.demo.persistenceLayer.mapper.UserSubjectsMapper;
import com.eam.demo.persistenceLayer.repository.UserSubjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserSubjectsDAO {

    private final UserSubjectsRepository userSubjectsRepository;
    private final UserSubjectsMapper userSubjectsMapper;

    public UserSubjectsDTO save(UserSubjectsDTO userSubjectsDTO) {
        UserSubjectsEntity entity = userSubjectsMapper.toEntity(userSubjectsDTO);
        UserSubjectsEntity savedEntity = userSubjectsRepository.save(entity);
        return userSubjectsMapper.toDTO(savedEntity);
    }

    public Optional<UserSubjectsDTO> findById(Long id) {
        return userSubjectsRepository.findById(id)
                .map(userSubjectsMapper::toDTO);
    }

    public List<UserSubjectsDTO> findAll() {
        List<UserSubjectsEntity> entities = userSubjectsRepository.findAll();
        return userSubjectsMapper.toDTOList(entities);
    }

    public List<UserSubjectsDTO> findByUserId(Long userId) {
        return userSubjectsMapper.toDTOList(userSubjectsRepository.findByUser(toUserEntity(userId)));
    }

    public Optional<UserSubjectsDTO> update(Long id, UserSubjectsDTO userSubjectsDTO) {
        return userSubjectsRepository.findById(id)
                .map(existingEntity -> {
                    userSubjectsMapper.updateEntityFromDTO(userSubjectsDTO, existingEntity);
                    UserSubjectsEntity updatedEntity = userSubjectsRepository.save(existingEntity);
                    return userSubjectsMapper.toDTO(updatedEntity);
                });
    }

    public boolean deleteById(Long id) {
        if (userSubjectsRepository.existsById(id)) {
            userSubjectsRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private UserEntity toUserEntity(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setIdUser(userId);
        return user;
    }
}
