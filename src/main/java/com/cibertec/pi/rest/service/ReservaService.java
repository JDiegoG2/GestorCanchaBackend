package com.cibertec.pi.rest.service;

import com.cibertec.pi.database.entidad.Cancha;
import com.cibertec.pi.database.repository.CanchaRepository;
import com.cibertec.pi.database.repository.ReservaRepository;
import com.cibertec.pi.rest.response.CanchaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CanchaRepository canchaRepository;

    public List<CanchaResponse> listarCanchasDisponibles(Long sedeId) {
        List<Cancha> canchaList = canchaRepository.findByEstadoIsTrueAndSedeId(sedeId);

        List<CanchaResponse> responses = new ArrayList<>();

        canchaList.forEach(bean -> {
            responses.add(new CanchaResponse(bean));
        });
        return responses;
    }

    public List<Integer> verHorasDisponibles(Long canchaId, String fecha){
        Cancha cancha = canchaRepository.findById(canchaId).get();
        List<Integer> horasDisponibles = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(fecha, formatter);

        for (int i = cancha.getDisHrInicio(); i < cancha.getDisHrFin(); i++) {
            horasDisponibles.add(i);
        }

        List<Integer> horasOcupadas = reservaRepository.verHorasOcupadas(canchaId, date);
        horasDisponibles.removeAll(horasOcupadas);

        return horasDisponibles;
    }

}
