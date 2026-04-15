package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.UserSubjectsDTO;
import com.eam.demo.persistenceLayer.entity.SubjectsEntity;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.UserSubjectsEntity;
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
public interface UserSubjectsMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "user.idUser")
    @Mapping(target = "subjectId", source = "subjects.id")
    @Mapping(target = "nota", source = "nota")
    UserSubjectsDTO toDTO(UserSubjectsEntity entity);

    List<UserSubjectsDTO> toDTOList(List<UserSubjectsEntity> entities);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "idToUser")
    @Mapping(target = "subject", source = "subjectId", qualifiedByName = "idToSubject")
    UserSubjectsEntity toEntity(UserSubjectsDTO dto);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserSubjectsDTO dto, @MappingTarget UserSubjectsEntity entity);

    @Named("idToUser")
    default UserEntity idToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setIdUser(userId);
        return user;
    }

    @Named("idToSubject")
    default SubjectsEntity idToSubject(Long subjectId) {
        if (subjectId == null) {
            return null;
        }
        SubjectsEntity subject = new SubjectsEntity();
        subject.setId(subjectId);
        return subject;
    }
}
