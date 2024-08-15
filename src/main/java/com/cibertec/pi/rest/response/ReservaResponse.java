package com.cibertec.pi.rest.response;

import com.cibertec.pi.database.entidad.Reserva;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class ReservaResponse {

    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("fecha_creacion")
    private String fechaCreacion;

    @JsonProperty("fecha_reserva")
    private String fechaReserva;

    @JsonProperty("hora_reserva")
    private Integer horaReserva;

    @JsonProperty("cancha")
    private String cancha;

    @JsonProperty("observacion")
    private String observacion;

    @JsonProperty("importe")
    private Double importe;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("cliente")
    private String cliente;

    public ReservaResponse(Reserva reserva) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.id = reserva.getId();
        this.fechaCreacion = dateFormat.format(reserva.getFechaCreacion());
        this.fechaReserva = reserva.getFechaReserva().format(formatter);
        this.horaReserva = reserva.getHoraReserva();
        this.cancha = reserva.getCancha().getNumero() + " | " + reserva.getCancha().getTipoCancha().name();
        this.observacion = reserva.getObservacion();
        this.importe = reserva.getImporte();
        this.estado = reserva.getEstado().name();
        this.cliente = reserva.getCliente().getPersona().getNombreCompleto();
    }
}
