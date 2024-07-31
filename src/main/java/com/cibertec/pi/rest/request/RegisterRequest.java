package com.cibertec.pi.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {
    @JsonProperty("username")
    private String username;

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
}
