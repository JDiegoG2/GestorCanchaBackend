package com.cibertec.pi.rest.service;

import com.cibertec.pi.database.entidad.Ubigeo;
import com.cibertec.pi.database.repository.UbigeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbigeoService {

    @Autowired
    private UbigeoRepository ubigeoRepository;

    // Lista todos los departamentos únicos
    public List<String> listaDepartamentos() {
        return ubigeoRepository.listaDepartamentos();
    }

    // Lista todas las provincias de un departamento específico
    public List<String> listaProvincias(String departamento) {
        return ubigeoRepository.listaProvincias(departamento);
    }

    // Lista todos los distritos de una provincia y departamento específicos
    public List<Ubigeo> listaDistritos(String departamento, String provincia) {
        return ubigeoRepository.listaDistritos(departamento, provincia);
    }

    // Busca ubigeos por departamento
    public List<Ubigeo> buscarPorDepartamento(String departamento) {
        return ubigeoRepository.findByDepartamento(departamento);
    }

    // Busca ubigeos por provincia
    public List<Ubigeo> buscarPorProvincia(String provincia) {
        return ubigeoRepository.findByProvincia(provincia);
    }

    // Busca ubigeos por distrito
    public List<Ubigeo> buscarPorDistrito(String distrito) {
        return ubigeoRepository.findByDistrito(distrito);
    }

    // Busca ubigeos por código
    public List<Ubigeo> buscarPorCodigo(String codigo) {
        return ubigeoRepository.findByCodigo(codigo);
    }

    // Crea un nuevo ubigeo
    public Ubigeo crearUbigeo(Ubigeo ubigeo) {
        return ubigeoRepository.save(ubigeo);
    }

    // Actualiza un ubigeo existente
    public Ubigeo actualizarUbigeo(Ubigeo ubigeo) {
        return ubigeoRepository.save(ubigeo);
    }

    // Elimina un ubigeo por ID
    public void eliminarUbigeo(Long id) {
        ubigeoRepository.deleteById(id);
    }
}
