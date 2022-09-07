package com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class Parada extends Espacio{

  public Parada(double latitud, double longitud, String provincia, String municipio, String localidad,
                String direccion, String numero, float codigoPostal) {
    super(latitud, longitud, provincia, municipio, localidad, direccion, numero, codigoPostal);
  }

  public Parada() {

  }
}
