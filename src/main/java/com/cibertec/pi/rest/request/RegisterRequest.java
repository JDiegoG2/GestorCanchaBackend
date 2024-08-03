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
public class RegisterRequest {

    @JsonProperty("password")
    private String password;

    @JsonProperty("nro_documento")
    private String nroDocumento;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("apellidos")
    private String apellidos;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("email")
    private String email;

    @JsonProperty("direccion")
    private String direccion;
}
