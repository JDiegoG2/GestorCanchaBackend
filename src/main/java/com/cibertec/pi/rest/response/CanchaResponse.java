package com.cibertec.pi.rest.response;

import com.cibertec.pi.constant.TipoCanchaEnum;
import com.cibertec.pi.database.entidad.Cancha;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CanchaResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("tipo_cancha")
    private TipoCanchaEnum tipoCancha;

    @JsonProperty("numero")
    private String numero;

    @JsonProperty("precio")
    private Double precio;

    @JsonProperty("sede_id")
    private Long sedeId;

    @JsonProperty("dis_hr_inicio")
    private LocalTime disHrInicio;

    @JsonProperty("dis_hr_fin")
    private LocalTime disHrFin;

    public CanchaResponse(Cancha cancha) {
        this.id = cancha.getId();
        this.tipoCancha = cancha.getTipoCancha();
        this.numero = cancha.getNumero();
        this.precio = cancha.getPrecio();
        this.sedeId = cancha.getSede().getId();
        this.disHrInicio = cancha.getDisHrInicio();
        this.disHrFin = cancha.getDisHrFin();
    }
}
