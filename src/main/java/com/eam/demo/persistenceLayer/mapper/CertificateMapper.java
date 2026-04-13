package com.eam.demo.persistenceLayer.mapper;

import com.eam.demo.business.dto.CertificateDTO;
import com.eam.demo.persistenceLayer.entity.CertificateEntity;
import com.eam.demo.persistenceLayer.entity.DocumentEntity;
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
public interface CertificateMapper {

    @Mapping(target = "idCertificate", source = "idCertificate")
    @Mapping(target = "documentId", source = "idDocument.id")
    @Mapping(target = "tipoCertificate", source = "tipoCertificate")
    @Mapping(target = "sign", source = "sign")
    @Mapping(target = "fechaEmision", source = "fechaEmision")
    CertificateDTO toDTO(CertificateEntity entity);

    List<CertificateDTO> toDTOList(List<CertificateEntity> entities);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "idCertificate", ignore = true)
    @Mapping(target = "idDocument", source = "documentId", qualifiedByName = "idToDocument")
    CertificateEntity toEntity(CertificateDTO dto);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "idCertificate", ignore = true)
    @Mapping(target = "idDocument", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CertificateDTO dto, @MappingTarget CertificateEntity entity);

    @Named("idToDocument")
    default DocumentEntity idToDocument(Long documentId) {
        if (documentId == null) {
            return null;
        }
        DocumentEntity document = new DocumentEntity();
        document.setId(documentId);
        return document;
    }
}
