package com.disenio.mimagrupo06.domain.sector;

import com.disenio.mimagrupo06.apiDistancia.Pais;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class ProvinciaSector extends Sector {
  public String nombre;

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

}
