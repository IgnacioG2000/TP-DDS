package com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ValorHCMensualMiembro {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  int anio;
  int mes;
  double huellaCarbono;

  public ValorHCMensualMiembro(int anio, int mes, double huellaCarbono) {
    this.anio = anio;
    this.mes = mes;
    this.huellaCarbono = huellaCarbono;
  }

  public ValorHCMensualMiembro() {

  }

  public boolean soyMes(int otroAnio, int otroMes) {
    return otroAnio == anio && otroMes == mes;
  }

  public double getHuellaCarbono() {
    return huellaCarbono;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getAnio() {
    return anio;
  }

  public void setAnio(int anio) {
    this.anio = anio;
  }

  public int getMes() {
    return mes;
  }

  public void setMes(int mes) {
    this.mes = mes;
  }

  public void setHuellaCarbono(double huellaCarbono) {
    this.huellaCarbono = huellaCarbono;
  }
}
