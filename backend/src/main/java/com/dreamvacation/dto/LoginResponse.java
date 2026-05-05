package com.dreamvacation.dto;

public class LoginResponse {
    private String usuario;
    private String mensaje;
    private boolean exitoso;

    public LoginResponse() {
    }

    public LoginResponse(String usuario, String mensaje, boolean exitoso) {
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.exitoso = exitoso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }
}
