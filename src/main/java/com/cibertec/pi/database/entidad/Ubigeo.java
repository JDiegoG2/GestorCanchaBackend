package com.cibertec.pi.database.entidad;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ubigeo")
public class Ubigeo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "codigo")
    private String codigo;// Código de ubigeo (6 dígitos: 2 para depto, 2 para provincia, 2 para distrito)


    @OneToMany(mappedBy = "ubigeo")  // Inverse side of the relation
    private List<Sede> sedes;
}
