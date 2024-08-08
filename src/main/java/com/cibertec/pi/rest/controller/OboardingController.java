package com.cibertec.pi.rest.controller;

import com.cibertec.pi.rest.request.LoginRequest;
import com.cibertec.pi.rest.request.RegisterRequest;
import com.cibertec.pi.rest.response.AuthResponse;
import com.cibertec.pi.rest.service.OnboardingService;
import com.cibertec.pi.util.GenericBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class OboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @Operation(summary = "Login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return onboardingService.login(loginRequest);
    }

    @Operation(summary = "Registro de Cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericBean.class))})
    })
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        return onboardingService.register(registerRequest);
    }
}
