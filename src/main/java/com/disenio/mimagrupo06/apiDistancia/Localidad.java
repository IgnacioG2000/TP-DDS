package com.disenio.mimagrupo06.apiDistancia;

public class Localidad {

  private String id;
  private String nombre;
  private String codPostal;
  private Municipio municipio;

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
