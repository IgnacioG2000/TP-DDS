package com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoActividad {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nombre;
  private double fe;
  private String tipoUnidad;

  public TipoActividad(String nombre, String tipoUnidad,double fe) {
    this.nombre = nombre;
    this.tipoUnidad = tipoUnidad;
    this.fe = fe;
  }

  public TipoActividad() {

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
