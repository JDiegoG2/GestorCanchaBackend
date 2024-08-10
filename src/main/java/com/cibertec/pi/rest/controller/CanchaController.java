package com.cibertec.pi.rest.controller;

import com.cibertec.pi.constant.TipoCanchaEnum;
import com.cibertec.pi.constant._Respuestas;
import com.cibertec.pi.database.entidad.Cancha;
import com.cibertec.pi.database.repository.CanchaRepository;
import com.cibertec.pi.rest.request.CrearCanchaRequest;
import com.cibertec.pi.rest.response.CanchaResponse;
import com.cibertec.pi.rest.service.CanchaService;
import com.cibertec.pi.util.GenericBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cancha")
@CrossOrigin("*")
public class CanchaController {

    @Autowired
    private CanchaService canchaService;
    @Autowired
    private CanchaRepository canchaRepository;

    @Operation(summary = "Creacion de Cancha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CanchaResponse.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @PostMapping("/guardar")
    public ResponseEntity crearCancha(@Valid @RequestBody CrearCanchaRequest request) {
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

    @Operation(summary = "Consultar Cancha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CanchaResponse.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @GetMapping("/listar")
    public ResponseEntity listar() {
        return canchaService.listarCancha();
    }

    @Operation(summary = "actualizar Cancha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CanchaResponse.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @PatchMapping("/{id}")
    public ResponseEntity actualizarCancha(@PathVariable("id") Long id, @Valid @RequestBody CrearCanchaRequest request) {
        return canchaService.actualizarCancha(id, request);
    }

    @Operation(summary = "Eliminar cancha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })

    @PatchMapping("/estado/{id}")
    public ResponseEntity actualizarEstadoCancha(@PathVariable("id") Long id, @RequestBody Map<String, Object> request) {
        Cancha cancha = canchaRepository.findById(id).orElse(null);
        if (cancha == null) {
            return _Respuestas.getErrorResult("La cancha no existe");
        }
        Boolean nuevoEstado = (Boolean) request.get("estado");
        cancha.setEstado(nuevoEstado);
        cancha = canchaRepository.save(cancha);
        return ResponseEntity.ok(new CanchaResponse(cancha));
    }


    @GetMapping("tipoCancha")
    public ResponseEntity<TipoCanchaEnum[]> getTiposCancha() {
        return ResponseEntity.ok(TipoCanchaEnum.values());
    }

}
