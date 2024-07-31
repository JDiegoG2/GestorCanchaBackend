package com.cibertec.pi.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginBean {
    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("token")
    private String token;

    @JsonProperty("nombre")
    private String nombre;
}
