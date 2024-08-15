package com.cibertec.pi.rest.service;

import com.cibertec.pi.database.entidad.Cancha;
import com.cibertec.pi.database.repository.CanchaRepository;
import com.cibertec.pi.rest.response.CanchaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private CanchaRepository canchaRepository;

    public List<CanchaResponse> listarCanchasDisponibles(Long sedeId) {
        List<Cancha> canchaList = canchaRepository.findByEstadoIsTrueAndSedeId(sedeId);

        List<CanchaResponse> responses = new ArrayList<>();

        canchaList.forEach(bean -> {
            responses.add(new CanchaResponse(bean));
        });
        return responses;
    }

}
