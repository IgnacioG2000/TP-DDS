package com.disenio.mimagrupo06.apiDistancia;

public class Provincia extends Sector{
  // Ver tipo ID
  public String id;
  public String nombre;
  public Pais pais;

  public String getNombre() {
    return this.nombre;
  }

  public String getId() {
    return id;
  }

  @Override
  public String nombreProvincia() {
    return nombre;
  }
}
