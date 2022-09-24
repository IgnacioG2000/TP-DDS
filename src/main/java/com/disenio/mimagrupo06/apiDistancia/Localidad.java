package com.disenio.mimagrupo06.apiDistancia;

import javax.persistence.*;


public class Localidad {

  private Long id;
  private String nombre;
  private String codPostal;
  private Municipio municipio;

  public Long getId() {
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
