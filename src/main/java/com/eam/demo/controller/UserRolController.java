package com.eam.demo.controller;

import com.eam.demo.business.dto.UserRolDTO;
import com.eam.demo.business.service.UserRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-roles")
@RequiredArgsConstructor
public class UserRolController {

    private final UserRolService userRolService;

    @GetMapping
    public ResponseEntity<List<UserRolDTO>> getAll() {
        return ResponseEntity.ok(userRolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRolDTO> getById(@PathVariable Long id) {
        return userRolService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRolDTO>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userRolService.getByUser(userId));
    }

    @GetMapping("/rol/{rolType}")
    public ResponseEntity<List<UserRolDTO>> getByRolType(@PathVariable String rolType) {
        return ResponseEntity.ok(userRolService.getByRolType(rolType));
    }

    @PostMapping("/assign")
    public ResponseEntity<UserRolDTO> assignRol(@RequestParam Long userId, @RequestParam Long rolId) {
        return userRolService.assignRol(userId, rolId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}/toggle-suspend")
    public ResponseEntity<UserRolDTO> toggleSuspend(@PathVariable Long id) {
        return userRolService.toggleSuspend(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeRol(@PathVariable Long id) {
        return userRolService.removeRol(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(userRolService.count());
    }
}
