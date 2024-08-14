package com.cibertec.pi.database.repository;

import com.cibertec.pi.database.entidad.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    Cancha findByNumero(String numero);


}
