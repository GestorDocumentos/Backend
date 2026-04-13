package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.DocumentDTO;
import com.eam.demo.persistenceLayer.entity.DocumentEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
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
public interface DocumentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "fecha", source = "fecha")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "usuarioId", source = "usuario.idUser")
    DocumentDTO toDTO(DocumentEntity entity);

    List<DocumentDTO> toDTOList(List<DocumentEntity> entities);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "idToUser")
    @Mapping(target = "certificate", ignore = true)
    DocumentEntity toEntity(DocumentDTO dto);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "certificate", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(DocumentDTO dto, @MappingTarget DocumentEntity entity);

    @Named("idToUser")
    default UserEntity idToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setIdUser(userId);
        return user;
    }
}
