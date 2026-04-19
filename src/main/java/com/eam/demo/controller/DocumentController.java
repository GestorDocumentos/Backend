package com.eam.demo.controller;

import com.eam.demo.persistenceLayer.entity.RequestType;
import com.eam.demo.business.service.impl.DocumentService;
import com.eam.demo.persistenceLayer.entity.TypeCertificateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/{userId}")
    public ResponseEntity<byte[]> generateDocument(
            @PathVariable Long userId,
            @RequestParam RequestType requestType,
            @RequestParam(required = false) TypeCertificateEntity certificateType
    ) {

        byte[] pdf = documentService.process(userId, requestType, certificateType);
        String fileName = requestType == RequestType.CERTIFICATE
                ? "certificacion.pdf"
                : "documento.pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
