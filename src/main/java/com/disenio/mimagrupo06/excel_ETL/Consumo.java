package com.disenio.mimagrupo06.excel_ETL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consumo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double valor;
  private String periocidad;

  public Consumo() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getValor() {
    return valor;
  }

  public String getPeriocidad() {
    return periocidad;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public void setPeriocidad(String periocidad) {
    this.periocidad = periocidad;
  }


}
