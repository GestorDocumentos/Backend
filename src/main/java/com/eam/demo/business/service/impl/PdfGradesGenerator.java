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

@Service
public class PdfGradesGenerator {

    public byte[] generate(UserReportDTO user) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("CERTIFICADO DE NOTAS"));
            document.add(new Paragraph("Estudiante: " + user.getName()));
            document.add(new Paragraph("ID: " + user.getIdUser()));

            Table table = new Table(2);
            table.addCell("Materia");
            table.addCell("Nota");

            for (SubjectReportDTO s : user.getSubjects()) {
                table.addCell(s.getSubjectName());
                table.addCell(String.valueOf(s.getNota()));
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out.toByteArray();
    }
}