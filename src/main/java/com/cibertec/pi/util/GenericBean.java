package com.cibertec.pi.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GenericBean {
    private String tipo;
    private String campo;
    private String mensaje;
}
