package com.disenio.mimagrupo06.apiDistancia;

import com.disenio.mimagrupo06.domain.sector.Sector;

public class Provincia {
  public String id;
  public String nombre;
  public Pais pais;

  public String getNombre() {
    return this.nombre;
  }

  public String getId() {
    return id;
  }

}
