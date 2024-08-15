package com.cibertec.pi.rest.controller;

import com.cibertec.pi.rest.response.CanchaResponse;
import com.cibertec.pi.rest.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping("/listar_canchas/{sede_id}")
    public List<CanchaResponse> listarCanchas(Long sede_id) {
        return reservaService.listarCanchasDisponibles(sede_id);
    }
}
