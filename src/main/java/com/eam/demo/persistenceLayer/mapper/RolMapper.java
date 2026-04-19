package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.RolDTO;
import com.eam.demo.persistenceLayer.entity.RolEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface RolMapper {

    @Mapping(target = "rolType", expression = "java(entity.getRolType().name())")
    RolDTO toDTO(RolEntity entity);

    List<RolDTO> toDTOList(List<RolEntity> entities);

    @Mapping(target = "idRol", ignore = true)
    @Mapping(target = "rolType", expression = "java(RolEntity.RolType.valueOf(dto.getRolType()))")
    RolEntity toEntity(RolDTO dto);
}