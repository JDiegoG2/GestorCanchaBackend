package com.cibertec.pi.database.repository;

import com.cibertec.pi.database.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByPersona_NroDocumento(String nroDocumento);
}
