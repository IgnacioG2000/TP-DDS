package com.disenio.mimagrupo06.presentacion.dto;


import java.time.LocalDate;
import java.util.Collection;

public class TrayectoNuevoDTO {


  private String nombreArea;
  private EspacioDTO espacioLlegada;
  private EspacioDTO espacioPartida;
  private Collection<TramoDTO> tramos;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private int diasUtilizados;

  public String getNombreArea() {
    return nombreArea;
  }

  public void setNombreArea(String nombreArea) {
    this.nombreArea = nombreArea;
  }

  public Collection<TramoDTO> getTramos() {
    return tramos;
  }

  public void setTramos(Collection<TramoDTO> tramos) {
    this.tramos = tramos;
  }

  public LocalDate getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(LocalDate fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public LocalDate getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
  }

  public int getDiasUtilizados() {
    return diasUtilizados;
  }

  public EspacioDTO getEspacioLlegada() {
    return espacioLlegada;
  }

  public void setEspacioLlegada(EspacioDTO espacioLlegada) {
    this.espacioLlegada = espacioLlegada;
  }

  public EspacioDTO getEspacioPartida() {
    return espacioPartida;
  }

  public void setEspacioPartida(EspacioDTO espacioPartida) {
    this.espacioPartida = espacioPartida;
  }

  public void setDiasUtilizados(int diasUtilizados) {
    this.diasUtilizados = diasUtilizados;
  }

}
