package com.cibertec.pi.database.repository;

import com.cibertec.pi.database.entidad.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbigeoRepository extends JpaRepository<Ubigeo, Long> {

    List<Ubigeo> findByDepartamento(String departamento);
    List<Ubigeo> findByProvincia(String provincia);
    List<Ubigeo> findByDistrito(String distrito);
    List<Ubigeo> findByCodigo(String codigo);

    // Consulta personalizada para obtener los departamentos únicos
    @Query("SELECT DISTINCT u.departamento FROM Ubigeo u")
    List<String> listaDepartamentos();

    // Consulta personalizada para obtener las provincias únicas en un departamento
    @Query("SELECT DISTINCT u.provincia FROM Ubigeo u WHERE u.departamento = :departamento")
    List<String> listaProvincias(String departamento);

    // Consulta personalizada para obtener los distritos de una provincia en un departamento
    @Query("SELECT u FROM Ubigeo u WHERE u.departamento = :departamento AND u.provincia = :provincia")
    List<Ubigeo> listaDistritos(String departamento, String provincia);
}
