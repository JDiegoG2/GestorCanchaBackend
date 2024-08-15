package com.cibertec.pi.rest.service;

import com.cibertec.pi.constant.EstadoReservaEnum;
import com.cibertec.pi.database.entidad.Cancha;
import com.cibertec.pi.database.entidad.Cliente;
import com.cibertec.pi.database.entidad.Reserva;
import com.cibertec.pi.database.repository.CanchaRepository;
import com.cibertec.pi.database.repository.ClienteRepository;
import com.cibertec.pi.database.repository.ReservaRepository;
import com.cibertec.pi.rest.request.CrearReservaRequest;
import com.cibertec.pi.rest.response.CanchaResponse;
import com.cibertec.pi.rest.response.ReservaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CanchaRepository canchaRepository;
    private final ClienteRepository clienteRepository;

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

    public ReservaResponse crearReserva(CrearReservaRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Cancha cancha = canchaRepository.findById(request.getCanchaId()).get();
        Cliente cliente = clienteRepository.findById(request.getClienteId()).get();
        Reserva reserva = Reserva.builder()
                .fechaCreacion(new Timestamp(new Date().getTime()))
                .fechaReserva(LocalDate.parse(request.getFechaReserva(), formatter))
                .horaReserva(request.getHoraReserva())
                .cancha(cancha)
                .observacion(request.getObservacion())
                .importe(request.getImporte())
                .estado(EstadoReservaEnum.PENDIENTE)
                .cliente(cliente)
                .build();
        reserva = reservaRepository.save(reserva);
        return new ReservaResponse(reserva);
    }


    public ReservaResponse obtenerReserva(Long reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId).get();
        return new ReservaResponse(reserva);
    }
}
