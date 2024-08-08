package com.cibertec.pi.database.entidad;

import com.cibertec.pi.constant.TipoCanchaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cancha")
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cancha")
    private TipoCanchaEnum tipoCancha;

    @Column(name = "numero")
    private String numero;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @Column(name = "dis_hr_inicio")
    private LocalTime disHrInicio;

    @Column(name = "dis_hr_fin")
    private LocalTime disHrFin;

    @Column(name = "estado")
    private Boolean estado;

}
