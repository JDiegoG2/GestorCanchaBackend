package com.cibertec.pi.database.entidad;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "persona")

@Data
public class Persona {
    @Id
    @Column(name = "nro_documento")
    private String nroDocumento;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "direccion")
    private String direccion;



    public String getNombreCompleto() {
        return String.format("%s %s", nombres, apellidos);
    }
}
