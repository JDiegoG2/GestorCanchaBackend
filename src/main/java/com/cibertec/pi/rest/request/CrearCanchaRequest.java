package com.cibertec.pi.rest.request;

import com.cibertec.pi.constant.TipoCanchaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearCanchaRequest {

    @NotNull(message = "Debe ingresar el tipo de cancha")
    @JsonProperty("tipo_cancha")
    private TipoCanchaEnum tipoCancha;

    @NotNull(message = "Debe ingresar el numero correcto")
    @Pattern(regexp = "^[0-9]*$", message = "El campo numero debe ser solo numerico")
    @JsonProperty("numero")
    private String numero;

    @NotNull(message = "Debe ingresar el precio")
    @JsonProperty("precio")
    private Double precio;

    @NotNull(message = "Debe ingresar el id de sede")
    @JsonProperty("sede_id")
    private Long sedeId;

    @NotNull(message = "Debe ingresar la hora de inicio")
    @Min(value = 0, message = "Valor minimo debe ser 0")
    @Max(value = 24, message = "Valor maximo no debe ser mayor a 24")
    @JsonProperty("dis_hr_inicio")
    private Integer disHrInicio;

    @NotNull(message = "Debe ingresar la hora de fin")
    @Min(value = 0, message = "Valor minimo debe ser 0")
    @Max(value = 24, message = "Valor maximo no debe ser mayor a 24")
    @JsonProperty("dis_hr_fin")
    private Integer disHrFin;
}
