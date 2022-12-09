package com.disenio.mimagrupo06.presentacion.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReporteMunicipioDTO {

  @Id
  @GeneratedValue
  private long id;

  private String municipio;
  private Integer anio;
  private Double hc_total;

  public ReporteMunicipioDTO() {

  }

  public ReporteMunicipioDTO(String municipio, Double hc_total) {
    this.municipio = municipio;
    this.hc_total = hc_total;
  }

  public ReporteMunicipioDTO(String municipio, Integer anio, Double hc_total) {
    this.municipio = municipio;
    this.hc_total = hc_total;
    this.anio = anio;
  }

  public Long getId() {
    return id;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public Integer getAnio() {
    return anio;
  }

  public void setAnio(Integer anio) {
    this.anio = anio;
  }

  public Double getHc_total() {
    return hc_total;
  }

  public void setHc_total(Double hc_total) {
    this.hc_total = hc_total;
  }
}
