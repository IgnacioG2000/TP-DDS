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


  public ProvinciaSector(Long id, String nombre, PaisSector pais) {
    this.id = id;
    this.nombre = nombre;
    this.pais = pais;
  }

  public ProvinciaSector() {

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
