package com.eam.demo.business.service.impl;

import com.eam.demo.business.dto.SubjectReportDTO;
import com.eam.demo.business.dto.UserReportDTO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

@Service
public class PdfSubjectsGenerator {
    private final PdfSignatureRenderer signatureRenderer;

    public PdfSubjectsGenerator(PdfSignatureRenderer signatureRenderer) {
        this.signatureRenderer = signatureRenderer;
    }

    public byte[] generate(UserReportDTO user) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("CERTIFICADO DE MATERIAS"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Se certifica que el estudiante: " + user.getName()));
            document.add(new Paragraph("Identificado con ID: " + user.getIdUser()));
            document.add(new Paragraph("Tiene registradas las siguientes materias:"));
            document.add(new Paragraph(" "));

            Table table = new Table(1);
            table.addCell("Materia");

            for (SubjectReportDTO subject : user.getSubjects()) {
                table.addCell(subject.getSubjectName());
            }

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Fecha: " + LocalDate.now()));
            signatureRenderer.addSignature(document);

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out.toByteArray();
    }
}
