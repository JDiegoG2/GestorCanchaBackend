package com.cibertec.pi.rest.controller;


import com.cibertec.pi.database.entidad.Cancha;
import com.cibertec.pi.database.entidad.Sede;
import com.cibertec.pi.rest.service.SedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sede")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SedeController {

    private final SedeService sedeService;



    @Operation(summary = "Listar todas las sedes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sedes listadas correctamente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Sede.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/listar")
    public List<Sede> listar() {
        return sedeService.getSedes();
    }

    @GetMapping({"/{id}"})
    @Operation(summary = "Obtener una sede por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sede encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Sede.class))}),
            @ApiResponse(responseCode = "404", description = "Sede no encontrada", content = @Content)
    })
    public ResponseEntity obtener(@PathVariable Long id) {
        return sedeService.getSede(id);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Crear una nueva sede")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sede creada correctamente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Sede.class))}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content)
    })
    public ResponseEntity<Sede> crear(@RequestBody Sede sede) {
        Sede nuevaSede = sedeService.save(sede);
        return ResponseEntity.status(201).body(nuevaSede);
    }

    @PutMapping({"/{id}"})
    @Operation(summary = "Actualizar una sede existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sede actualizada correctamente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Sede.class))}),
            @ApiResponse(responseCode = "404", description = "Sede no encontrada", content = @Content)
    })
    public ResponseEntity<Sede> actualizar(@PathVariable Long id, @RequestBody Sede sedeDetalles) {

        return sedeService.update(id, sedeDetalles);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sede existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sede eliminada correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sede no encontrada", content = @Content)
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return sedeService.delete(id);
    }

    @PatchMapping("/estado/{id}")
    public ResponseEntity<?> actualizarEstadoSede(@PathVariable("id") Long id, @RequestBody Map<String, Object> request) {
        Sede sede = sedeService.findById(id).orElse(null); // Busca la sede por ID
        if (sede == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La sede no existe");
        }

        // Obtiene el estado desde el request y asegura que no sea null
        Integer estadoValue = (Integer) request.get("estado");
        if (estadoValue == null) {
            return ResponseEntity.badRequest().body("El estado es requerido");
        }

        // Convierte el valor entero a booleano
        Boolean nuevoEstado = (estadoValue == 1);
        sede.setEstado(nuevoEstado); // Actualiza el estado de la sede
        sede = sedeService.save(sede); // Guarda los cambios

        return ResponseEntity.ok(sede);
    }
}
