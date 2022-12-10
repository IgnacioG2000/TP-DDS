package com.disenio.mimagrupo06.presentacion.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReportePaisDTO {

  @Id
  @GeneratedValue
  private long id;

  private String pais;
  private String provincia;
  private Double hc_total;

  public ReportePaisDTO() {

  }

  public ReportePaisDTO(String pais, String provincia, Double hc_total) {
    this.pais = pais;
    this.provincia = provincia;
    this.hc_total = hc_total;
  }

  public long getId() {
    return id;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public Double getHc_total() {
    return hc_total;
  }

  public void setHc_total(Double hc_total) {
    this.hc_total = hc_total;
  }
}
