package com.cibertec.pi.rest.controller;

import com.cibertec.pi.rest.request.CrearReservaRequest;
import com.cibertec.pi.rest.response.CanchaResponse;
import com.cibertec.pi.rest.response.ReservaResponse;
import com.cibertec.pi.rest.service.PdfService;
import com.cibertec.pi.rest.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reserva")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final PdfService pdfService;

    @GetMapping("/listar_canchas/{sede_id}")
    public List<CanchaResponse> listarCanchas(@PathVariable("sede_id") Long sedeId) {
        return reservaService.listarCanchasDisponibles(sedeId);
    }

    @GetMapping("/listar_horario/{cancha_id}/{fecha}")
    public List<Integer> listarHorarios(@PathVariable("cancha_id") Long canchaId, @PathVariable("fecha") String fecha) {
        return reservaService.verHorasDisponibles(canchaId, fecha);
    }

    @PostMapping
    public ResponseEntity<byte[]> crearReserva(@RequestBody CrearReservaRequest request){
        ReservaResponse reservaResponse = reservaService.crearReserva(request);

        // Generar el PDF del ticket
        byte[] pdfTicket = pdfService.generatePdfTicket(reservaResponse);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "ticket-reserva.pdf");


        return new ResponseEntity<>(pdfTicket, headers, HttpStatus.CREATED);
    }


    @GetMapping("/{reserva_id}")
    public ReservaResponse detalleReserva(@PathVariable("reserva_id") Long reservaId){
        return reservaService.obtenerReserva(reservaId);
    }
}
