package com.cibertec.pi.rest.controller;

import com.cibertec.pi.database.entidad.Ubigeo;
import com.cibertec.pi.rest.service.UbigeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ubigeo")
@CrossOrigin("*")
public class UbigeoController {

    @Autowired
    private UbigeoService ubigeoService;

    // Endpoint para listar todos los departamentos
    @GetMapping("/departamentos")
    public ResponseEntity<List<String>> listarDepartamentos() {
        List<String> departamentos = ubigeoService.listaDepartamentos();
        return ResponseEntity.ok(departamentos);
    }

    // Endpoint para listar todas las provincias de un departamento específico
    @GetMapping("/provincias/{departamento}")
    public ResponseEntity<List<String>> listarProvincias(@PathVariable String departamento) {
        List<String> provincias = ubigeoService.listaProvincias(departamento);
        return ResponseEntity.ok(provincias);
    }

    // Endpoint para listar todos los distritos de una provincia y departamento específicos
    @GetMapping("/distritos/{departamento}/{provincia}")
    public ResponseEntity<List<Ubigeo>> listarDistritos(@PathVariable String departamento, @PathVariable String provincia) {
        List<Ubigeo> distritos = ubigeoService.listaDistritos(departamento, provincia);
        return ResponseEntity.ok(distritos);
    }

    // Endpoint para buscar ubigeos por departamento
    @GetMapping("/buscar/departamento/{departamento}")
    public ResponseEntity<List<Ubigeo>> buscarPorDepartamento(@PathVariable String departamento) {
        List<Ubigeo> ubigeos = ubigeoService.buscarPorDepartamento(departamento);
        return ResponseEntity.ok(ubigeos);
    }

    // Endpoint para buscar ubigeos por provincia
    @GetMapping("/buscar/provincia/{provincia}")
    public ResponseEntity<List<Ubigeo>> buscarPorProvincia(@PathVariable String provincia) {
        List<Ubigeo> ubigeos = ubigeoService.buscarPorProvincia(provincia);
        return ResponseEntity.ok(ubigeos);
    }

    // Endpoint para buscar ubigeos por distrito
    @GetMapping("/buscar/distrito/{distrito}")
    public ResponseEntity<List<Ubigeo>> buscarPorDistrito(@PathVariable String distrito) {
        List<Ubigeo> ubigeos = ubigeoService.buscarPorDistrito(distrito);
        return ResponseEntity.ok(ubigeos);
    }

    // Endpoint para buscar ubigeos por código
    @GetMapping("/buscar/codigo/{codigo}")
    public ResponseEntity<List<Ubigeo>> buscarPorCodigo(@PathVariable String codigo) {
        List<Ubigeo> ubigeos = ubigeoService.buscarPorCodigo(codigo);
        return ResponseEntity.ok(ubigeos);
    }

    // Endpoint para crear un nuevo ubigeo
    @PostMapping("/crear")
    public ResponseEntity<Ubigeo> crearUbigeo(@RequestBody Ubigeo ubigeo) {
        Ubigeo nuevoUbigeo = ubigeoService.crearUbigeo(ubigeo);
        return ResponseEntity.ok(nuevoUbigeo);
    }

    // Endpoint para actualizar un ubigeo existente
    @PutMapping("/actualizar")
    public ResponseEntity<Ubigeo> actualizarUbigeo(@RequestBody Ubigeo ubigeo) {
        Ubigeo ubigeoActualizado = ubigeoService.actualizarUbigeo(ubigeo);
        return ResponseEntity.ok(ubigeoActualizado);
    }

    // Endpoint para eliminar un ubigeo por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUbigeo(@PathVariable Long id) {
        ubigeoService.eliminarUbigeo(id);
        return ResponseEntity.noContent().build();
    }
}
