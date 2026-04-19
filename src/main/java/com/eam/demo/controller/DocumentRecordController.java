package com.eam.demo.controller;

import com.eam.demo.business.dto.DocumentDTO;
import com.eam.demo.persistenceLayer.dao.DocumentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document-records")
@RequiredArgsConstructor
public class DocumentRecordController {

    private final DocumentDAO documentDAO;

    @PostMapping
    public ResponseEntity<DocumentDTO> create(@RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentDAO.save(documentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getById(@PathVariable Long id) {
        return documentDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAll() {
        return ResponseEntity.ok(documentDAO.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DocumentDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(documentDAO.findByUsuarioId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> update(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        return documentDAO.update(id, documentDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return documentDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
