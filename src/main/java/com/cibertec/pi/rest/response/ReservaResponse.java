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

    @JsonProperty("pdf_url")
    private String pdfUrl;

    // Constructor que acepta un objeto Reserva
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

    // Constructor que acepta parámetros individuales
    public ReservaResponse(Long id, String fechaCreacion, String fechaReserva, Integer horaReserva, String cancha, String observacion, Double importe, String estado, String cliente, String pdfUrl) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.cancha = cancha;
        this.observacion = observacion;
        this.importe = importe;
        this.estado = estado;
        this.cliente = cliente;
        this.pdfUrl = pdfUrl;
    }
}
