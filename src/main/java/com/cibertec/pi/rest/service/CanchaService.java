package com.cibertec.pi.rest.service;

import com.cibertec.pi.constant._Respuestas;
import com.cibertec.pi.database.entidad.Cancha;
import com.cibertec.pi.database.repository.CanchaRepository;
import com.cibertec.pi.database.repository.SedeRepository;
import com.cibertec.pi.rest.request.CrearCanchaRequest;
import com.cibertec.pi.rest.response.CanchaResponse;
import com.cibertec.pi.util.GenericBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CanchaService {

    private final CanchaRepository canchaRepository;
    private final SedeRepository sedeRepository;

    public ResponseEntity crearCancha(CrearCanchaRequest request) {
        if (canchaRepository.findByNumero(request.getNumero()) != null) {
            return _Respuestas.getErrorResult("El numero de cancha ya existe");
        }
        if (request.getDisHrInicio().equals(request.getDisHrFin()) || request.getDisHrInicio().isAfter(request.getDisHrFin())) {
            return _Respuestas.getErrorResult("Las horas de disponibilidad estan mal ingresadas");
        }


        Cancha cancha = new Cancha();
        cancha.setNumero(request.getNumero());
        cancha.setPrecio(request.getPrecio());
        cancha.setSede(sedeRepository.findById(request.getSedeId()).orElse(null));
        cancha.setDisHrInicio(request.getDisHrInicio());
        cancha.setDisHrFin(request.getDisHrFin());
        cancha.setEstado(true);
        cancha = canchaRepository.save(cancha);

        return ResponseEntity.ok(new CanchaResponse(cancha));
    }

    public ResponseEntity listarCancha() {
        List<Cancha> canchaList = canchaRepository.findAllByEstadoIsTrue();
        List<CanchaResponse> responses = new ArrayList<>();
        canchaList.forEach(bean -> responses.add(new CanchaResponse(bean)));
        return ResponseEntity.ok(responses);
    }

    public ResponseEntity getById(Long id) {
        Cancha cancha = canchaRepository.findById(id).orElse(null);
        if (cancha == null) {
            return _Respuestas.getErrorResult("El cancha no existe");
        }
        return ResponseEntity.ok(new CanchaResponse(cancha));
    }

    public ResponseEntity actualizarCancha(Long id, CrearCanchaRequest request) {
        Cancha cancha = canchaRepository.findById(id).orElse(null);
        if (cancha == null) {
            return _Respuestas.getErrorResult("El cancha no existe");
        }
        cancha.setNumero(request.getNumero());
        cancha.setPrecio(request.getPrecio());
        cancha.setDisHrInicio(request.getDisHrInicio());
        cancha.setDisHrFin(request.getDisHrFin());
        cancha.setEstado(true);
        cancha = canchaRepository.save(cancha);
        return ResponseEntity.ok(new CanchaResponse(cancha));
    }

    public ResponseEntity eliminarCancha(Long id) {
        Cancha cancha = canchaRepository.findById(id).orElse(null);
        if (cancha == null) {
            return _Respuestas.getErrorResult("El cancha no existe");
        }
        cancha.setEstado(false);
        cancha = canchaRepository.save(cancha);
        return _Respuestas.getSuccessResult();
    }
}
