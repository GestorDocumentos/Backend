package com.eam.demo.persistenceLayer.dao;

import com.eam.demo.business.dto.UserDTO;
import com.eam.demo.persistenceLayer.entity.RolEntity;
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

    /**
     * CREATE - Crear nuevo usuario
     *
     * FLUJO:
     * 1. UserDTO -> UserEntity (mapper toEntity())
     * 2. Guardar Entity en BD
     * 3. Entity guardada -> UserDTO
     *
     * NOTA:
     * - idUser es la cédula, viene del DTO
     * - roles y subjects se gestionan aparte
     */
    public UserDTO save(UserDTO userDTO) {
        UserEntity entity = userMapper.toEntity(userDTO);
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDTO(savedEntity);
    }

    /**
     * READ - Buscar usuario por ID (cédula)
     *
     * @return Optional<UserDTO> - empty si no existe
     */
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    /**
     * READ - Buscar usuario por email
     *
     * @return Optional<UserDTO> - empty si no existe
     */
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO);
    }

    /**
     * READ ALL - Buscar todos los usuarios
     *
     * NOTA: En aplicaciones grandes considerar paginación
     */
    public List<UserDTO> findAll() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    /**
     * UPDATE - Actualizar usuario existente usando @MappingTarget
     *
     * FLUJO:
     * 1. Buscar UserEntity existente por ID
     * 2. Usar mapper.updateEntityFromDTO() para modificar la entity
     * 3. Guardar entity modificada
     * 4. Retornar DTO actualizado
     *
     * COMPORTAMIENTO:
     * - Campos null en UserDTO se ignoran
     * - idUser, roles, subjects no se modifican
     */
    public Optional<UserDTO> update(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existingEntity -> {
                    userMapper.updateEntityFromDTO(userDTO, existingEntity);
                    UserEntity updatedEntity = userRepository.save(existingEntity);
                    return userMapper.toDTO(updatedEntity);
                });
    }

    /**
     * DELETE - Eliminar usuario por ID
     *
     * @return boolean - true si se eliminó, false si no existía
     */
    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * UTILIDAD - Verificar si existe usuario por ID
     */
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    /**
     * UTILIDAD - Contar total de usuarios
     */
    public long count() {
        return userRepository.count();
    }
}