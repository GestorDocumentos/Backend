package com.eam.demo.business.service.impl;


import com.eam.demo.business.dto.UserReportDTO;
import com.eam.demo.persistenceLayer.entity.DocumentEntity;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

@Service
public class PdfStudyGenerator {
    private static final String DEFAULT_SIGNATURE = "Firma: Director Academico";

    public byte[] generate(UserReportDTO user) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("CERTIFICADO DE ESTUDIO"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Se certifica que el estudiante: " + user.getName()));
            document.add(new Paragraph("Identificado con ID: " + user.getIdUser()));
            document.add(new Paragraph("Se encuentra activo en la institución."));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Fecha: " + LocalDate.now()));
            document.add(new Paragraph(DEFAULT_SIGNATURE));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out.toByteArray();
    }

    // 📄 Documento básico
    public byte[] generateBasic(DocumentEntity documentEntity) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("DOCUMENTO GENERADO"));
            document.add(new Paragraph("ID: " + documentEntity.getId()));
            document.add(new Paragraph("Estudiante: " + documentEntity.getUsuario().getName()));
            document.add(new Paragraph("ID estudiante: " + documentEntity.getUsuario().getIdUser()));
            document.add(new Paragraph("Fecha: " + documentEntity.getFecha()));
            document.add(new Paragraph(DEFAULT_SIGNATURE));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out.toByteArray();
    }
}
