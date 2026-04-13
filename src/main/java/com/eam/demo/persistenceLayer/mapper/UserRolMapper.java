package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.UserRolDTO;
import com.eam.demo.persistenceLayer.entity.RolEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.UserRolEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserRolMapper {

    @Mapping(target = "idUserRol", source = "idUserRol")
    @Mapping(target = "userId", source = "user.idUser")
    @Mapping(target = "rolId", source = "rol.idRol")
    @Mapping(target = "suspended", source = "suspended")
    UserRolDTO toDTO(UserRolEntity entity);

    List<UserRolDTO> toDTOList(List<UserRolEntity> entities);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "idUserRol", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "idToUser")
    @Mapping(target = "rol", source = "rolId", qualifiedByName = "idToRol")
    UserRolEntity toEntity(UserRolDTO dto);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "idUserRol", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserRolDTO dto, @MappingTarget UserRolEntity entity);

    @Named("idToUser")
    default UserEntity idToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setIdUser(userId);
        return user;
    }

    @Named("idToRol")
    default RolEntity idToRol(Long rolId) {
        if (rolId == null) {
            return null;
        }
        RolEntity rol = new RolEntity();
        rol.setIdRol(rolId);
        return rol;
    }
}
