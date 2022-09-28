package com.disenio.mimagrupo06.excel_ETL;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.TipoActividad;

import javax.persistence.*;

@Entity
public class DatosDeLaActividad {
  private String actividad;
  @ManyToOne
  private TipoActividad tipoActividad;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne
  private Consumo consumo;
  private String periodoDeImputacion;

  public DatosDeLaActividad() {
  }

  public String getActividad() {
    return actividad;
  }

  public String getTipoDeConsumo() {
    return tipoActividad.getNombre();
  }

  public TipoActividad getTipoActividad() {
    return tipoActividad;
  }

  public void setTipoActividad(TipoActividad tipoActividad) {
    this.tipoActividad = tipoActividad;
  }

  public Consumo getConsumo() {
    return consumo;
  }

  public String getPeriodoDeImputacion() {
    return periodoDeImputacion;
  }

  public void setActividad(String actividad) {
    this.actividad = actividad;
  }


  public void setConsumo(Consumo consumo) {
    this.consumo = consumo;
  }

  public void setPeriodoDeImputacion(String periodoDeImputacion) {
    this.periodoDeImputacion = periodoDeImputacion;
  }

  public boolean perteneceAnio(int anio) {
    String[] fechas = periodoDeImputacion.split("/");
    int fechaAnio;
    if(fechas.length > 1){
      fechaAnio = Integer.parseInt(fechas[1]);
    }else{
      fechaAnio = Integer.parseInt(fechas[0]);
    }
    return fechaAnio == anio;
  }

  public boolean perteneceMesAnio(int anio, int mes) {
    String[] fechas = periodoDeImputacion.split("/");
    boolean rta;
    int fechaAnio;
    int fechaMes;
    if(fechas.length > 1){
      fechaAnio = Integer.parseInt(fechas[1]);
      fechaMes = Integer.parseInt(fechas[0]);
      rta = fechaAnio == anio && fechaMes == mes;
    }else{
      rta = false;
    }
    return rta;
  }

  public boolean perteneceSoloAnio(int anio) {
    String[] fechas = periodoDeImputacion.split("/");
    boolean rta;
    int fechaAnio;
    if(fechas.length == 1){
      fechaAnio = Integer.parseInt(fechas[0]);
      rta = fechaAnio == anio;
    }else{
      rta = false;
    }
    return rta;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getId() {
    return id;
  }
}
