package com.cibertec.pi.rest.controller;


import com.cibertec.pi.database.entidad.Sede;
import com.cibertec.pi.rest.service.SedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
