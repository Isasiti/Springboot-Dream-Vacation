package com.dreamvacation.service;

import com.dreamvacation.dto.LoginRequest;
import com.dreamvacation.dto.LoginResponse;
import com.dreamvacation.model.Usuario;
import com.dreamvacation.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Autentica un usuario con nombre de usuario y contraseña
     * @param loginRequest Datos de login
     * @return LoginResponse con el resultado de la autenticación
     */
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(loginRequest.getNombreUsuario());
        
        if (usuario.isPresent()) {
            Usuario usuarioEncontrado = usuario.get();
            // Validar contraseña (en producción usar BCrypt)
            if (usuarioEncontrado.getContrasena().equals(loginRequest.getContrasena())) {
                return new LoginResponse(
                    usuarioEncontrado.getId(),
                    usuarioEncontrado.getNombreUsuario(),
                    usuarioEncontrado.getEmail(),
                    "Login exitoso",
                    true
                );
            } else {
                return new LoginResponse(
                    null, null, null,
                    "Contraseña incorrecta",
                    false
                );
            }
        } else {
            return new LoginResponse(
                null, null, null,
                "Usuario no encontrado",
                false
            );
        }
    }

    /**
     * Registra un nuevo usuario
     * @param usuario Datos del usuario a registrar
     * @return Usuario registrado
     */
    public Usuario registrar(Usuario usuario) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene un usuario por su ID
     * @param id ID del usuario
     * @return Usuario encontrado
     */
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}
