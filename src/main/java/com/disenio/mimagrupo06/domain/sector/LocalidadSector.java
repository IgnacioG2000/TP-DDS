package com.disenio.mimagrupo06.domain.sector;

import com.disenio.mimagrupo06.apiDistancia.Municipio;

public class LocalidadSector
{
    private String id;
    private String nombre;
    private String codPostal;
    private Municipio municipio;


    public LocalidadSector(String id, String nombre, String codPostal, Municipio municipio) {
        this.id = id;
        this.nombre = nombre;
        this.codPostal = codPostal;
        this.municipio = municipio;
    }

    public LocalidadSector() {

    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

}
