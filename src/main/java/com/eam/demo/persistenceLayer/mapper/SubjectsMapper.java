package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.SubjectsDTO;
import com.eam.demo.persistenceLayer.entity.SubjectsEntity;
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
public interface SubjectsMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    SubjectsDTO toDTO(SubjectsEntity entity);

    List<SubjectsDTO> toDTOList(List<SubjectsEntity> entities);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    SubjectsEntity toEntity(SubjectsDTO dto);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(SubjectsDTO dto, @MappingTarget SubjectsEntity entity);
}
