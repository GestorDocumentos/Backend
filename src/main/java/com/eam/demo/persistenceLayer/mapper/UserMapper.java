package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.UserDTO;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserMapper {

    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    UserDTO toDTO(UserEntity entity);

    List<UserDTO> toDTOList(List<UserEntity> entities);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "documents", ignore = true)
    UserEntity toEntity(UserDTO dto);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserDTO dto, @MappingTarget UserEntity entity);
}
