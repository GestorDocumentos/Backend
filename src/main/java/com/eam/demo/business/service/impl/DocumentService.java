package com.eam.demo.business.service.impl;

import com.eam.demo.business.dto.UserReportDTO;
import  com.eam.demo.persistenceLayer.entity.RequestType;
import com.eam.demo.persistenceLayer.entity.*;
import com.eam.demo.persistenceLayer.mapper.ReportMapper;
import com.eam.demo.persistenceLayer.repository.CertificateRepository;
import com.eam.demo.persistenceLayer.repository.DocumentRepository;
import com.eam.demo.persistenceLayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final CertificateRepository certificateRepository;
    private final ReportMapper reportMapper;

    private final PdfStudyGenerator studyGenerator;
    private final PdfGradesGenerator gradesGenerator;

    public byte[] process(Long userId,
                          RequestType requestType,
                          TypeCertificateEntity certificateType) {

        // 1. Buscar usuario
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Crear DOCUMENTO
        DocumentEntity document = new DocumentEntity();
        document.setTipo(requestType.name());
        document.setFecha(LocalDate.now());
        document.setEstado(DocumentStatusEntity.EMITIDO);
        document.setUsuario(user);

        documentRepository.save(document);

        // 🔹 SOLO DOCUMENTO
        if (requestType == RequestType.DOCUMENT) {
            return generateBasicDocument(document);
        }

        // 🔹 VALIDACIÓN
        if (certificateType == null) {
            throw new IllegalArgumentException("Debe enviar el tipo de certificado");
        }

        // 3. Crear CERTIFICADO
        CertificateEntity certificate = new CertificateEntity();
        certificate.setIdDocument(document);
        certificate.setTipoCertificate(certificateType);
        certificate.setFechaEmision(LocalDate.now());
        certificate.setSign("Firma Director");

        certificateRepository.save(certificate);

        // 4. MAPEAR A DTO
        UserReportDTO reportDTO = reportMapper.toDTO(user);

        // 5. GENERAR PDF
        switch (certificateType) {
            case NOTAS:
                return gradesGenerator.generate(reportDTO);

            case ESTUDIO:
                return studyGenerator.generate(reportDTO);

            default:
                throw new IllegalArgumentException("Tipo no soportado");
        }
    }

    // 📄 Documento básico
    public byte[] generateBasicDocument(DocumentEntity document) {
        return studyGenerator.generateBasic(document);
    }
}