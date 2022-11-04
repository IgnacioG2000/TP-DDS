package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Hogar;

public class HogarDTO {
    private Hogar hogar;
    private String nombreArea;

    public Hogar getHogar() {
        return hogar;
    }

    public void setHogar(Hogar hogar) {
        this.hogar = hogar;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
}
