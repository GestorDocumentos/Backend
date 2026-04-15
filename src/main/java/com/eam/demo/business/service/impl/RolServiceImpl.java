package com.eam.demo.business.service.impl;

import com.eam.demo.business.dto.RolDTO;
import com.eam.demo.business.service.RolService;
import com.eam.demo.persistenceLayer.dao.RolDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolDAO rolDAO;

    @Override public List<RolDTO> getAll() { return rolDAO.findAll(); }
    @Override public Optional<RolDTO> getById(Long id) { return rolDAO.findById(id); }
    @Override public Optional<RolDTO> getByRolType(String rolType) { return rolDAO.findByRolType(rolType); }
    @Override public RolDTO create(RolDTO dto) { return rolDAO.save(dto); }
    @Override public Optional<RolDTO> update(Long id, RolDTO dto) { return rolDAO.update(id, dto); }
    @Override public boolean delete(Long id) { return rolDAO.deleteById(id); }
    @Override public long count() { return rolDAO.count(); }
}