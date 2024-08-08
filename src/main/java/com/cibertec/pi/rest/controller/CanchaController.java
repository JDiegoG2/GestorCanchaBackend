package com.cibertec.pi.rest.controller;

import com.cibertec.pi.rest.request.CrearCanchaRequest;
import com.cibertec.pi.rest.response.CanchaResponse;
import com.cibertec.pi.rest.service.CanchaService;
import com.cibertec.pi.util.GenericBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cancha")
@CrossOrigin("*")
public class CanchaController {

    @Autowired
    private CanchaService canchaService;

    @Operation(summary = "Creacion de Cancha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CanchaResponse.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @PostMapping
    public ResponseEntity crearCancha(@RequestBody CrearCanchaRequest request) {
        return canchaService.crearCancha(request);
    }

    @Operation(summary = "Consultar Cancha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CanchaResponse.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return canchaService.getById(id);
    }

    @Operation(summary = "actualizar Cancha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CanchaResponse.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @PatchMapping("/{id}")
    public ResponseEntity actualizarCancha(@PathVariable("id") Long id, @RequestBody CrearCanchaRequest request) {
        return canchaService.actualizarCancha(id, request);
    }

    @Operation(summary = "Eliminar cancha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity listarCancha(@PathVariable("id") Long id) {
        return canchaService.eliminarCancha(id);
    }

}
