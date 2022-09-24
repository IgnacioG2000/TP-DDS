package com.disenio.mimagrupo06.apiDistancia;

import com.disenio.mimagrupo06.domain.sector.Sector;

public class Municipio {
  public String id;
  public String nombre;
  public Provincia provincia;

  public String getNombre() {
    return this.nombre;
  }

  public String getId() {
    return this.id;
  }

  public Provincia getProvincia() {
    return provincia;
  }

}
