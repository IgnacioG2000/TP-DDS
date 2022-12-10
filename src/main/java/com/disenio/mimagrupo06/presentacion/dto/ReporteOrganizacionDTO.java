package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.organizacion.Clasificacion;
import com.disenio.mimagrupo06.domain.organizacion.TipoDeOrganizacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReporteOrganizacionDTO {

  @Id
  @GeneratedValue
  private long id;

  private TipoDeOrganizacion tipo_de_organizacion;
  private Clasificacion clasificacion;
  private String razon_social;
  private Integer anio;
  private Double hc_total;

  public ReporteOrganizacionDTO() {
  }

  public ReporteOrganizacionDTO(TipoDeOrganizacion tipo_de_organizacion, Clasificacion clasificacion, Double hc_total) {
    this.tipo_de_organizacion = tipo_de_organizacion;
    this.clasificacion = clasificacion;
    this.hc_total = hc_total;
  }

  public ReporteOrganizacionDTO(String razon_social, Integer anio, Double hc_total) {
    this.razon_social = razon_social;
    this.anio = anio;
    this.hc_total = hc_total;
  }

  public long getId() {
    return id;
  }

  public TipoDeOrganizacion getTipo_de_organizacion() {
    return tipo_de_organizacion;
  }

  public void setTipo_de_organizacion(TipoDeOrganizacion tipo_de_organizacion) {
    this.tipo_de_organizacion = tipo_de_organizacion;
  }

  public Clasificacion getClasificacion() {
    return clasificacion;
  }

  public void setClasificacion(Clasificacion clasificacion) {
    this.clasificacion = clasificacion;
  }

  public String getRazon_social() {
    return razon_social;
  }

  public void setRazon_social(String razon_social) {
    this.razon_social = razon_social;
  }

  public Double getHc_total() {
    return hc_total;
  }

  public void setHc_total(Double hc_total) {
    this.hc_total = hc_total;
  }

  public Integer getAnio() {
    return anio;
  }

  public void setAnio(Integer anio) {
    this.anio = anio;
  }
}
