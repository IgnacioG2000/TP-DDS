package com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_st",discriminatorType = DiscriminatorType.INTEGER)
public class Espacio {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  protected double latitud;
  protected double longitud;
  protected String provincia;
  protected String municipio;
  protected String localidad;
  protected String direccion;
  protected String numero;
  protected float codigoPostal;

  public Espacio(double latitud, double longitud, String provincia, String municipio,
                 String localidad, String direccion, String numero, float codigoPostal) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.provincia = provincia;
    this.municipio = municipio;
    this.localidad = localidad;
    this.direccion = direccion;
    this.numero = numero;
    this.codigoPostal = codigoPostal;
  }

  public Espacio() {

  }

  public double getLatitud() {
    return latitud;
  }

  public void setLatitud(double latitud) {
    this.latitud = latitud;
  }

  public double getLongitud() {
    return longitud;
  }

  public void setLongitud(double longitud) {
    this.longitud = longitud;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {  this.numero = numero; }

  public float getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(float codigoPostal) {

    this.codigoPostal = codigoPostal;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int discriminante(){
    return 0;
  };

}
