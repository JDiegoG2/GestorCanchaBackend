package com.cibertec.pi.constant;

import com.cibertec.pi.util.GenericBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class _Respuestas {

    public static ResponseEntity<GenericBean> getSuccessResult() {
        GenericBean respuesta = new GenericBean();
        respuesta.setTipo("Exitoso");
        respuesta.setMensaje("Se realizó solicitud con éxito");
        return ResponseEntity.ok(respuesta);
    }

    public static ResponseEntity<GenericBean> getErrorResult(String mensaje) {
        GenericBean genericBean = new GenericBean();
        genericBean.setTipo("error");
        genericBean.setMensaje(mensaje);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericBean);
    }
}
