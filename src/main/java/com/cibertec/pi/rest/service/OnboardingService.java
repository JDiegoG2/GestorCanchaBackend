package com.cibertec.pi.rest.service;

import com.cibertec.pi.config.jwt.JwtService;
import com.cibertec.pi.constant._Respuestas;
import com.cibertec.pi.database.entidad.Persona;
import com.cibertec.pi.constant.RoleEnum;
import com.cibertec.pi.database.entidad.Cliente;
import com.cibertec.pi.database.repository.PersonaRepository;
import com.cibertec.pi.database.repository.ClienteRepository;
import com.cibertec.pi.rest.request.LoginRequest;
import com.cibertec.pi.rest.request.RegisterRequest;
import com.cibertec.pi.rest.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public ResponseEntity<?> login(LoginRequest request) {
        // Buscar el usuario por email
        Cliente cliente = clienteRepository.findByEmail(request.getEmail()).orElse(null);
        if (cliente == null || !passwordEncoder.matches(request.getPassword(), cliente.getContrasena())) {
            return _Respuestas.getErrorResult("Usuario o contraseña incorrecto");
        }

        // Autenticar al usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // Generar el token JWT
        String token = jwtService.getToken(cliente);
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .userId(cliente.getId())
                .perfil(cliente.getRole().name())
                .build();
        return ResponseEntity.ok(authResponse);
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        // Verifica si el usuario ya existe por email
        if (clienteRepository.findByEmail(request.getEmail()).isPresent()) {
            return _Respuestas.getErrorResult("El correo ya está en uso");
        }

        // Busca la persona por documento
        Cliente cliente = clienteRepository.findByPersona_NroDocumento(request.getNroDocumento()).orElse(null);
        if (cliente != null) {
            return _Respuestas.getErrorResult("El número de documento ya está registrado");
        }

        // Crea y guarda la nueva persona
        Persona persona = new Persona();
        persona.setNroDocumento(request.getNroDocumento());
        persona.setNombres(request.getNombres());
        persona.setApellidos(request.getApellidos());
        persona.setEmail(request.getEmail());
        persona.setTelefono(request.getTelefono());
        persona.setDireccion(request.getDireccion());
        personaRepository.save(persona);

        // Codifica la contraseña antes de guardar el usuario
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Crea y guarda el nuevo usuario
        cliente = new Cliente();
        cliente.setPersona(persona);
        cliente.setEmail(request.getEmail());
        cliente.setContrasena(encodedPassword);
        cliente.setRole(RoleEnum.USER);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(_Respuestas.getSuccessResult());
    }
    }

