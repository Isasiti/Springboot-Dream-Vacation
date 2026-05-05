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

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findById(loginRequest.getUsuario());

        if (usuario.isPresent()) {
            Usuario encontrado = usuario.get();
            if (encontrado.getContrasena().equals(loginRequest.getContrasena())) {
                return new LoginResponse(
                    encontrado.getUsuario(),
                    "Login exitoso",
                    true
                );
            }
            return new LoginResponse(
                null,
                "Contraseña incorrecta",
                false
            );
        }
        return new LoginResponse(
            null,
            "Usuario no encontrado",
            false
        );
    }
}
