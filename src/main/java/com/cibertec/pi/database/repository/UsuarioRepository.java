package com.cibertec.pi.database.repository;

import com.cibertec.pi.database.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
