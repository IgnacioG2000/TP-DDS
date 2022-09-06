package com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC;

public class TipoActividad {
  String nombre;
  double fe;
  String tipoUnidad;

  public TipoActividad(String nombre, String tipoUnidad,double fe) {
    this.nombre = nombre;
    this.tipoUnidad = tipoUnidad;
    this.fe = fe;
  }

  public String getNombre() {
    return nombre;
  }

  public double getFe() {
    return fe;
  }

  public String getTipoUnidad() {
    return tipoUnidad;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setFe(double fe) {
    this.fe = fe;
  }

  public void setTipoUnidad(String tipoUnidad) {
    this.tipoUnidad = tipoUnidad;
  }

}
