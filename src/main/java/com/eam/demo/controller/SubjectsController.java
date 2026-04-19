package com.eam.demo.controller;

import com.eam.demo.business.dto.SubjectsDTO;
import com.eam.demo.persistenceLayer.dao.SubjectsDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectsController {

    private final SubjectsDAO subjectsDAO;

    @PostMapping
    public ResponseEntity<SubjectsDTO> create(@RequestBody SubjectsDTO subjectsDTO) {
        return ResponseEntity.ok(subjectsDAO.save(subjectsDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectsDTO> getById(@PathVariable Long id) {
        return subjectsDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SubjectsDTO>> getAll() {
        return ResponseEntity.ok(subjectsDAO.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectsDTO> update(@PathVariable Long id, @RequestBody SubjectsDTO subjectsDTO) {
        return subjectsDAO.update(id, subjectsDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return subjectsDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
