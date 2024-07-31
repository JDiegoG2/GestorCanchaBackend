package com.cibertec.pi.database.repository;

import com.cibertec.pi.database.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, String> {
}
