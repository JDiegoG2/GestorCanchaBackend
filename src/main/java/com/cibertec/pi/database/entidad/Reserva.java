package com.cibertec.pi.database.entidad;

import com.cibertec.pi.constant.EstadoReservaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @Column(name = "fecha_reserva")
    private LocalDate fechaReserva;

    @Column(name = "hora_reserva")
    private Integer horaReserva;

    @ManyToOne
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "importe")
    private Double importe;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoReservaEnum estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
