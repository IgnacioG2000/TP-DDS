package com.disenio.mimagrupo06.presentacion.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReporteProvincia {

  @Id
  @GeneratedValue
  private long id;

  private String provincia;
  private Double hc_total;

  public ReporteProvincia(String provincia, Double hc_total) {
    this.provincia = provincia;
    this.hc_total = hc_total;
  }

  public ReporteProvincia() {

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

  public Long getId() {
    return id;
  }
}
