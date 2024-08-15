package com.cibertec.pi.database.repository;

import com.cibertec.pi.database.entidad.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = "select r.horaReserva from Reserva r where r.cancha.id = :canchaId and r.fechaReserva = :fechaReserva and r.estado != 'COMPLETA'")
    public List<Integer> verHorasOcupadas(@Param("canchaId") Long canchaId, @Param("fechaReserva") LocalDate fechaReserva);

}
