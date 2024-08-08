package com.cibertec.pi.rest.request;

import com.cibertec.pi.constant.TipoCanchaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearCanchaRequest {

    @JsonProperty("tipo_cancha")
    private TipoCanchaEnum tipoCancha;

    @JsonProperty("numero")
    private String numero;

    @JsonProperty("precio")
    private Double precio;

    @JsonProperty("sede_id")
    private Long sedeId;

    @JsonProperty("dis_hr_inicio")
    private Integer disHrInicio;

    @JsonProperty("dis_hr_fin")
    private Integer disHrFin;
}
