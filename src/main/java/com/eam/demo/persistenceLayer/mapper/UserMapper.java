package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.UserDTO;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(entity.getRoles() != null ? entity.getRoles().stream().map(r -> r.getRol().getRolType().name()).toList() : java.util.Collections.emptyList())")
    @Mapping(target = "subjects", ignore = true)
    UserDTO toDTO(UserEntity entity);

    List<UserDTO> toDTOList(List<UserEntity> entities);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "documents", ignore = true)
    UserEntity toEntity(UserDTO dto);

    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserDTO dto, @MappingTarget UserEntity entity);
}
