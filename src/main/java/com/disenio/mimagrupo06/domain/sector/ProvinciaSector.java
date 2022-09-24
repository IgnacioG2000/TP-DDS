package com.disenio.mimagrupo06.domain.sector;

import com.disenio.mimagrupo06.apiDistancia.Pais;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("1")
public class ProvinciaSector extends Sector {
  public String nombre;
  @ManyToOne
  public PaisSector pais;
  public Long provinciaCodigo;

  public ProvinciaSector(Long provinciaCodigo, String nombre, PaisSector pais) {
    this.provinciaCodigo = provinciaCodigo;
    this.nombre = nombre;
    this.pais = pais;
  }

  public ProvinciaSector() {

  }

  public Long getProvinciaCodigo() {
    return provinciaCodigo;
  }

  public void setProvinciaCodigo(Long provinciaCodigo) {
    this.provinciaCodigo = provinciaCodigo;
  }

  @Override
  public String getNombre() {
    return nombre;
  }

  @Override
  public String nombreProvincia() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public PaisSector getPais() {
    return pais;
  }

  public void setPais(PaisSector pais) {
    this.pais = pais;
  }
}
