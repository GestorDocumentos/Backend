package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.RolDTO;
import com.eam.demo.persistenceLayer.entity.RolEntity;
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
public interface RolMapper {

    @Mapping(target = "idRol", source = "idRol")
    @Mapping(target = "rolType", source = "rolType")
    RolDTO toDTO(RolEntity entity);

    List<RolDTO> toDTOList(List<RolEntity> entities);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "idRol", ignore = true)
    RolEntity toEntity(RolDTO dto);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "idRol", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(RolDTO dto, @MappingTarget RolEntity entity);
}
