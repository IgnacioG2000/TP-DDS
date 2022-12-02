package com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("2")
public class Hogar extends Espacio{
  private int piso;
  private String departamento;
  @Enumerated(EnumType.ORDINAL)
  private TipoDeHogar tipoDeHogar;

  public Hogar(double latitud, double longitud, String provincia, String municipio, String localidad,
               String direccion, String numero, float codigoPostal, int piso, String departamento,
               TipoDeHogar tipoDeHogar) {
    super(latitud, longitud, provincia, municipio, localidad, direccion, numero, codigoPostal);
    this.piso = piso;
    this.departamento = departamento;
    this.tipoDeHogar = tipoDeHogar;
  }

  public Hogar() {

  }

  @Override
  public int discriminante() {
    return 2;
  }

  public int getPiso() {
    return piso;
  }

  public void setPiso(int piso) {
    this.piso = piso;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public TipoDeHogar getTipoDeHogar() {
    return tipoDeHogar;
  }

  public void setTipoDeHogar(TipoDeHogar tipoDeHogar) {
    this.tipoDeHogar = tipoDeHogar;
  }
}
