package com.disenio.mimagrupo06.presentacion.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReporteProvincia {

  @Id
  @GeneratedValue
  private Long id;

  private String provincia;
  private String hc_total;

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public String getHc_total() {
    return hc_total;
  }

  public void setHc_total(String hc_total) {
    this.hc_total = hc_total;
  }

  public Long getId() {
    return id;
  }
}
