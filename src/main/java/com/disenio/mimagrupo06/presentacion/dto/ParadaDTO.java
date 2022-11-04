package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Parada;

public class ParadaDTO {
    private Parada parada;
    private String nombreArea;

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
}
