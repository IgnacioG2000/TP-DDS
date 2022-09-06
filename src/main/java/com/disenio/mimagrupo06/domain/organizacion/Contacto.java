package com.disenio.mimagrupo06.domain.organizacion;

public class Contacto {
    private String email;
    private String numeroTelefono;

    public Contacto(String email, String numeroTelefono) {
        this.email = email;
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
