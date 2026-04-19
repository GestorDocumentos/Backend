package com.eam.demo.business.service.impl;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class PdfSignatureRenderer {

    private static final String SIGNATURE_RESOURCE = "signatures/signature.png";
    private static final String DEFAULT_SIGNATURE_TEXT = "Vanessa Ayala";

    public void addSignature(Document document) {
        try {
            ClassPathResource resource = new ClassPathResource(SIGNATURE_RESOURCE);
            if (resource.exists()) {
                Image image = new Image(ImageDataFactory.create(resource.getURL()));
                image.setAutoScale(false);
                image.setWidth(150);
                image.setHeight(60);
                image.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Firma Rectora").setTextAlignment(TextAlignment.CENTER));
                document.add(image);
                document.add(new Paragraph(DEFAULT_SIGNATURE_TEXT).setTextAlignment(TextAlignment.CENTER));
                return;
            }
        } catch (Exception ignored) {
        }

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Firma Rectora").setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph(DEFAULT_SIGNATURE_TEXT).setTextAlignment(TextAlignment.CENTER));
    }
}
