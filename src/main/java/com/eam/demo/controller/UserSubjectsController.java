package com.eam.demo.controller;

import com.eam.demo.business.dto.UserSubjectsDTO;
import com.eam.demo.persistenceLayer.dao.UserSubjectsDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-subjects")
@RequiredArgsConstructor
public class UserSubjectsController {

    private final UserSubjectsDAO userSubjectsDAO;

    @PostMapping
    public ResponseEntity<UserSubjectsDTO> create(@RequestBody UserSubjectsDTO userSubjectsDTO) {
        return ResponseEntity.ok(userSubjectsDAO.save(userSubjectsDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSubjectsDTO> getById(@PathVariable Long id) {
        return userSubjectsDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserSubjectsDTO>> getAll() {
        return ResponseEntity.ok(userSubjectsDAO.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSubjectsDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userSubjectsDAO.findByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSubjectsDTO> update(@PathVariable Long id, @RequestBody UserSubjectsDTO userSubjectsDTO) {
        return userSubjectsDAO.update(id, userSubjectsDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return userSubjectsDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
