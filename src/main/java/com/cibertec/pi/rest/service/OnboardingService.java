package com.cibertec.pi.rest.service;

import com.cibertec.pi.JWT.JwtService;
import com.cibertec.pi.constant._Respuestas;
import com.cibertec.pi.database.entidad.Persona;
import com.cibertec.pi.database.entidad.Role;
import com.cibertec.pi.database.entidad.Usuario;
import com.cibertec.pi.database.repository.PersonaRepository;
import com.cibertec.pi.database.repository.UsuarioRepository;
import com.cibertec.pi.rest.request.LoginRequest;
import com.cibertec.pi.rest.request.RegisterRequest;
import com.cibertec.pi.rest.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public ResponseEntity<?> login(LoginRequest request) {
        // Autenticar al usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // Buscar el usuario por email
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElse(null);
        if (usuario == null || !passwordEncoder.matches(request.getPassword(), usuario.getContrasena())) {
            return _Respuestas.getErrorResult("Usuario o contraseña incorrecto");
        }

        // Generar el token JWT
        String token = jwtService.getToken((UserDetails) usuario);
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .build();
        return ResponseEntity.ok(authResponse);
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        // Verifica si el usuario ya existe por email
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return _Respuestas.getErrorResult("El correo ya está en uso");
        }

        // Busca la persona por documento
        Persona persona = personaRepository.findById(request.getNroDocumento()).orElse(null);
        if (persona != null) {
            return _Respuestas.getErrorResult("El número de documento ya está registrado");
        }

        // Crea y guarda la nueva persona
        persona = new Persona();
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
        Usuario usuario = new Usuario();
        usuario.setPersona(persona);
        usuario.setEmail(request.getEmail());
        usuario.setContrasena(encodedPassword);
        usuario.setRole(Role.USER);
        usuarioRepository.save(usuario);

        // Generar el token JWT
        String token = jwtService.getToken((UserDetails) usuario);
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .build();
        return ResponseEntity.ok(authResponse);
    }
    }

