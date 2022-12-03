package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.TipoDeHogar;

public class EspacioDTO {
    private Long id;
    private String clase;
    private int pisoDepartamento;
    private String departamento;
    private String pisoTrabajo;
    private String unidad;
    private double latitud;
    private double longitud;
    private  String municipio;
    private String localidad;
    private String provincia;
    private String direccion;
    private String numero;
    private float codigoPostal;
    private TipoDeHogar tipoDeHogar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getPisoDepartamento() {
        return pisoDepartamento;
    }

    public void setPisoDepartamento(int pisoDepartamento) {
        this.pisoDepartamento = pisoDepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPisoTrabajo() {
        return pisoTrabajo;
    }

    public void setPisoTrabajo(String pisoTrabajo) {
        this.pisoTrabajo = pisoTrabajo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitudTramoPartida) {
        this.latitud = latitudTramoPartida;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public float getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(float codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public TipoDeHogar getTipoDeHogar() {
        return tipoDeHogar;
    }

    public void setTipoDeHogar(TipoDeHogar tipoDeHogar) {
        this.tipoDeHogar = tipoDeHogar;
    }
}

