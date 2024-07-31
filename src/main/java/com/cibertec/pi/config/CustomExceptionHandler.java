package com.cibertec.pi.config;

import com.cibertec.pi.util.GenericBean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GenericBean handleException(MethodArgumentNotValidException ex) {

        GenericBean genericBean = new GenericBean();
        ex.getBindingResult().getAllErrors().forEach(bean -> {
            String errorMessage = bean.getDefaultMessage();
            if (bean instanceof FieldError) {
                genericBean.setCampo(((FieldError) bean).getField());
            }
            genericBean.setTipo("Error");
            genericBean.setMensaje(errorMessage);
        });

        return genericBean;
    }
}
