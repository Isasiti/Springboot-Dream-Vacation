package com.dreamvacation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long usuarioId;
    private String nombreUsuario;
    private String email;
    private String mensaje;
    private boolean exitoso;
}
