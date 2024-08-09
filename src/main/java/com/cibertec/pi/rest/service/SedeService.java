package com.cibertec.pi.rest.service;

import com.cibertec.pi.constant._Respuestas;
import com.cibertec.pi.database.entidad.Sede;
import com.cibertec.pi.database.repository.SedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SedeService {

    private final SedeRepository sedeRepository;

    public List<Sede> getSedes() {
        return sedeRepository.findAllByEstadoIsTrue();
    }

    public ResponseEntity getSede(Long id) {
        Sede sede = sedeRepository.findById(id).orElse(null);
        if (sede == null) {
            return _Respuestas.getErrorResult("La sede no existe");
        }
        return ResponseEntity.ok(sede);
    }

    public Sede save(Sede sede) {
        sede.setEstado(true);
        return sedeRepository.save(sede);
    }

    public ResponseEntity delete(Long id) {
        Sede sede = sedeRepository.findById(id).orElse(null);
        if (sede == null) {
            return _Respuestas.getErrorResult("La sede no existe");
        }
        sede.setEstado(false);
        sedeRepository.save(sede);
        return _Respuestas.getSuccessResult();
    }

    public ResponseEntity update(Long id, Sede update) {
        Sede sede = sedeRepository.findById(id).orElse(null);
        if (sede == null) {
            return _Respuestas.getErrorResult("La sede no existe");
        }
        sede.setNombre(update.getNombre());
        sede.setDireccion(update.getDireccion());
        sede.setTelefono(update.getTelefono());
        sedeRepository.save(sede);

        return ResponseEntity.ok(sede);
    }
}
