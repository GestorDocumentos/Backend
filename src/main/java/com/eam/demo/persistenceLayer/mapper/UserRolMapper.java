package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.UserRolDTO;
import com.eam.demo.persistenceLayer.entity.UserRolEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserRolMapper {

    @Mapping(target = "userId", source = "user.idUser")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "rol", expression = "java(entity.getRol().getRolType().name())")
    @Mapping(target = "suspended", source = "suspended")
    UserRolDTO toDTO(UserRolEntity entity);

    List<UserRolDTO> toDTOList(List<UserRolEntity> entities);

    @Mapping(target = "idUserRol", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "rol", ignore = true)
    UserRolEntity toEntity(UserRolDTO dto);

    @Mapping(target = "idUserRol", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserRolDTO dto, @MappingTarget UserRolEntity entity);
}