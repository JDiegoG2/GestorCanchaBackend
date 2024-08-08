package com.cibertec.pi.rest.service;

import com.cibertec.pi.config.ResourceNotFoundException;
import com.cibertec.pi.database.entidad.Sede;
import com.cibertec.pi.database.repository.SedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SedeService {

    private final SedeRepository sedeRepository;

    @Transactional(readOnly = true)
    public List<Sede> getSedes() {
        return sedeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Sede> getSede(Long id) {
        return sedeRepository.findById(id);
    }

    @Transactional
    public Sede save(Sede sede) {
        return sedeRepository.save(sede);
    }

    @Transactional
    public void delete(Sede sede) {
        sedeRepository.delete(sede);
    }

    @Transactional
    public Sede update(Sede sede) {
        if (!sedeRepository.existsById(sede.getId())) {
            throw new ResourceNotFoundException("Sede no encontrada con id: " + sede.getId());
        }
        return sedeRepository.save(sede);
    }
}
