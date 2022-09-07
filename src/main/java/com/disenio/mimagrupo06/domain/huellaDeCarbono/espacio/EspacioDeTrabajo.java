package com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class EspacioDeTrabajo extends Espacio{
  private int piso;
  private String unidad;

  public EspacioDeTrabajo(Double latitud, Double longitud, String provincia, String municipio, String localidad,
                          String direccion, String numero, float codigoPostal, int piso, String unidad) {
    super(latitud,longitud,provincia,municipio, localidad, direccion, numero,codigoPostal);

    this.piso=piso;
    this.unidad=unidad;
  }

  public EspacioDeTrabajo() {

  }

  public int getPiso() {
    return piso;
  }

  public void setPiso(int piso) {
    this.piso = piso;
  }

  public String getUnidad() {
    return unidad;
  }

  public void setUnidad(String unidad) {
    this.unidad = unidad;
  }
}
