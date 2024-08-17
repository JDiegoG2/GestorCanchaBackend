package com.cibertec.pi.rest.service;

import com.cibertec.pi.constant._Respuestas;
import com.cibertec.pi.database.entidad.Sede;
import com.cibertec.pi.database.entidad.Ubigeo;
import com.cibertec.pi.database.repository.SedeRepository;
import com.cibertec.pi.database.repository.UbigeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SedeService {

    private final SedeRepository sedeRepository;
    private final UbigeoRepository ubigeoRepository;

    public List<Sede> getSedes() {
        return sedeRepository.findAll();
    }

    public ResponseEntity getSede(Long id) {
        Sede sede = sedeRepository.findById(id).orElse(null);
        if (sede == null) {
            return _Respuestas.getErrorResult("La sede no existe");
        }
        return ResponseEntity.ok(sede);
    }

    public Sede save(Sede sede) {
        Ubigeo ubigeo = ubigeoRepository.findById(Optional.ofNullable(sede.getUbigeo()).map(Ubigeo::getId).orElse(0L)).orElse(null);
        sede.setUbigeo(ubigeo);
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
        sede.setUbigeo(ubigeoRepository.findById(Optional.ofNullable(update.getUbigeo()).map(Ubigeo::getId).orElse(0L)).orElse(null));
        sedeRepository.save(sede);

        return ResponseEntity.ok(sede);
    }
    public Optional<Sede> findById(Long id) {
        return sedeRepository.findById(id);
    }

    // Método para listar sedes activas
    public List<Sede> listarSedesActivas() {
        return sedeRepository.findByEstado(true);
    }
}
