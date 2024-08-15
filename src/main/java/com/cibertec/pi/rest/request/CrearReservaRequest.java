package com.cibertec.pi.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearReservaRequest {


    @JsonProperty("fecha_reserva")
    private String fechaReserva;

    @JsonProperty("hora_reserva")
    private Integer horaReserva;

    @JsonProperty("cancha_id")
    private Long canchaId;

    @JsonProperty("observacion")
    private String observacion;

    @JsonProperty("importe")
    private Double importe;

    @JsonProperty("cliente_id")
    private Long clienteId;
}
