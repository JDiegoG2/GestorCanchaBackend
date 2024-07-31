package com.cibertec.pi.rest.service;

import com.cibertec.pi.constant._Respuestas;
import com.cibertec.pi.database.entidad.Persona;
import com.cibertec.pi.database.entidad.Usuario;
import com.cibertec.pi.database.repository.PersonaRepository;
import com.cibertec.pi.database.repository.UsuarioRepository;
import com.cibertec.pi.rest.request.LoginRequest;
import com.cibertec.pi.rest.request.RegisterRequest;
import com.cibertec.pi.rest.response.LoginBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public ResponseEntity login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsuario(request.getUsername()).orElse(null);
        if(usuario == null) return _Respuestas.getErrorResult("Usuario o contraseña incorrecto");
        if (!Objects.equals(request.getPassword(), usuario.getContrasena())){
            return _Respuestas.getErrorResult("Usuario o contraseña incorrecto");
        }
        LoginBean loginBean = new LoginBean();
        loginBean.setUsuario(request.getUsername());
        loginBean.setNombre(usuario.getPersona().getNombreCompleto());

        return ResponseEntity.ok(loginBean);
    }

    public ResponseEntity register(RegisterRequest request) {
        Persona persona = personaRepository.findById(request.getNroDocumento()).orElse(null);
        if(persona == null) {
            persona = new Persona();
            persona.setNroDocumento(request.getNroDocumento());
            persona.setNombres(request.getNombres());
            persona.setApellidos(request.getApellidos());
            persona.setEmail(request.getEmail());
            persona.setTelefono(request.getTelefono());
            personaRepository.save(persona);
        }
        Usuario usuario = new Usuario();
        usuario.setPersona(persona);
        usuario.setUsuario(request.getUsername());
        usuario.setContrasena(request.getPassword());
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }
}
