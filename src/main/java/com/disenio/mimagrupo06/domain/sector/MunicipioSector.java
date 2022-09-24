package com.disenio.mimagrupo06.domain.sector;

import com.disenio.mimagrupo06.apiDistancia.Provincia;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class MunicipioSector extends Sector{
  public String nombre;
  @ManyToOne
  public ProvinciaSector provincia;
  public Long municipioCodigo;

  public MunicipioSector(Long municipioCodigo, String nombre, ProvinciaSector provincia) {
    this.municipioCodigo = municipioCodigo;
    this.nombre = nombre;
    this.provincia = provincia;
   // this.listaLocalidades = datosApi.buscarLocalidadesDe(this);
  }

  public MunicipioSector() {

  }

  public ProvinciaSector getProvincia() {
    return provincia;
  }

  public void setProvincia(ProvinciaSector provincia) {
    this.provincia = provincia;
  }

  public Long getMunicipioCodigo() {
    return municipioCodigo;
  }

  public void setMunicipioCodigo(Long municipioCodigo) {
    this.municipioCodigo = municipioCodigo;
  }

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
