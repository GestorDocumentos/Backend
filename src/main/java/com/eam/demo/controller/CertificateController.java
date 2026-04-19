package com.eam.demo.controller;

import com.eam.demo.business.dto.CertificateDTO;
import com.eam.demo.persistenceLayer.dao.CertificateDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateDAO certificateDAO;

    @PostMapping
    public ResponseEntity<CertificateDTO> create(@RequestBody CertificateDTO certificateDTO) {
        return ResponseEntity.ok(certificateDAO.save(certificateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateDTO> getById(@PathVariable Long id) {
        return certificateDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CertificateDTO>> getAll() {
        return ResponseEntity.ok(certificateDAO.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateDTO> update(@PathVariable Long id, @RequestBody CertificateDTO certificateDTO) {
        return certificateDAO.update(id, certificateDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return certificateDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
