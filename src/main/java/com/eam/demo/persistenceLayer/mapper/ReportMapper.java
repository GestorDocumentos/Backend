package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.SubjectReportDTO;
import com.eam.demo.business.dto.UserReportDTO;
import com.eam.demo.persistenceLayer.entity.UserEntity;
import com.eam.demo.persistenceLayer.entity.UserSubjectsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "subjects", source = "subjects")
    UserReportDTO toDTO(UserEntity entity);

    List<UserReportDTO> toDTOList(List<UserEntity> entities);

    @Mapping(target = "subjectName", source = "subjects.name")
    @Mapping(target = "nota", source = "nota")
    SubjectReportDTO toSubjectDTO(UserSubjectsEntity entity);

    List<SubjectReportDTO> toSubjectDTOList(List<UserSubjectsEntity> entities);
}