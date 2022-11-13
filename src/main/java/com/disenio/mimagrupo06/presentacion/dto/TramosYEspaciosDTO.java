package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;

import java.util.List;

public class TramosYEspaciosDTO {
    private Iterable<Tramo> tramos;
    private Iterable<Espacio> espacios;

    public TramosYEspaciosDTO(Iterable<Tramo> tramos, Iterable<Espacio> espacios) {
        this.tramos = tramos;
        this.espacios = espacios;
    }

    public Iterable<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(Iterable<Tramo> tramos) {
        this.tramos = tramos;
    }

    public Iterable<Espacio> getEspacios() {
        return espacios;
    }

    public void setEspacios(Iterable<Espacio> espacios) {
        this.espacios = espacios;
    }
}
