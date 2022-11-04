package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.EspacioDeTrabajo;

public class EspacioDeTrabajoDTO {
    private EspacioDeTrabajo espacioDeTrabajo;
    private String nombreArea;

    public EspacioDeTrabajo getEspacioDeTrabajo() {
        return espacioDeTrabajo;
    }

    public void setEspacioDeTrabajo(EspacioDeTrabajo espacioDeTrabajo) {
        this.espacioDeTrabajo = espacioDeTrabajo;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
}
