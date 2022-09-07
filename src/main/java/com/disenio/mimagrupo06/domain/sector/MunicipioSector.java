package com.disenio.mimagrupo06.domain.sector;

import com.disenio.mimagrupo06.apiDistancia.Provincia;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("2")
public class MunicipioSector extends Sector{
  public String nombre;
  @ManyToOne
  public ProvinciaSector provincia;

  @Override
  public String getNombre() {
    return nombre;
  }

  @Override
  public String nombreProvincia() {
    return provincia.getNombre();
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public ProvinciaSector getProvinciaSector() {
    return provincia;
  }

  public void setProvinciaSector(ProvinciaSector provincia) {
    this.provincia = provincia;
  }
}
