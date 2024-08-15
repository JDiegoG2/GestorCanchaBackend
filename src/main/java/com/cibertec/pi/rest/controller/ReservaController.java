package com.cibertec.pi.rest.controller;

import com.cibertec.pi.rest.request.CrearReservaRequest;
import com.cibertec.pi.rest.response.CanchaResponse;
import com.cibertec.pi.rest.response.ReservaResponse;
import com.cibertec.pi.rest.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping("/listar_canchas/{sede_id}")
    public List<CanchaResponse> listarCanchas(@PathVariable("sede_id") Long sede_id) {
        return reservaService.listarCanchasDisponibles(sede_id);
    }

    @GetMapping("/listar_horario/{cancha_id}/{fecha}")
    public List<Integer> listarHorarios(@PathVariable("cancha_id") Long cancha_id, @PathVariable("fecha") String fecha) {
        return reservaService.verHorasDisponibles(cancha_id, fecha);
    }

    @PostMapping
    public ReservaResponse crearReserva(CrearReservaRequest request){
        return reservaService.crearReserva(request);
    }

    @GetMapping("/{reserva_id}")
    public ReservaResponse detalleReserva(@PathVariable("reserva_id") Long reservaId){
        return reservaService.obtenerReserva(reservaId);
    }
}
