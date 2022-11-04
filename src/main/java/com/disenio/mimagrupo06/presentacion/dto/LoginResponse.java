package com.disenio.mimagrupo06.presentacion.dto;

import java.util.List;

public class LoginResponse {

    private String idSesion;
    private List<String> areas;

    public LoginResponse(String idSesion, List<String> areas) {
        this.idSesion = idSesion;
        this.areas = areas;

    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }
}
