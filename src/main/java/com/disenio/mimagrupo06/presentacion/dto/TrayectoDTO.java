package com.disenio.mimagrupo06.presentacion.dto;


import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;

import java.time.LocalDate;
import java.util.Collection;

public class TrayectoDTO {

  private String nombreArea;
  private String discriminadorPartida;
  private String discriminadorLlegada;
  private Espacio espacioLlegada;
  private Espacio espacioPartida;
  private Collection<Tramo> tramos;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private int diasUtilizados;

  public String getNombreArea() {
    return nombreArea;
  }

  public void setNombreArea(String nombreArea) {
    this.nombreArea = nombreArea;
  }

  public Collection<Tramo> getTramos() {
    return tramos;
  }

  public void setTramos(Collection<Tramo> tramos) {
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

  public Espacio getEspacioLlegada() {
    return espacioLlegada;
  }

  public void setEspacioLlegada(Espacio espacioLlegada) {
    this.espacioLlegada = espacioLlegada;
  }

  public Espacio getEspacioPartida() {
    return espacioPartida;
  }

  public void setEspacioPartida(Espacio espacioPartida) {
    this.espacioPartida = espacioPartida;
  }

  public void setDiasUtilizados(int diasUtilizados) {
    this.diasUtilizados = diasUtilizados;
  }

  public String getDiscriminadorPartida() {
    return discriminadorPartida;
  }

  public void setDiscriminadorPartida(String discriminadorPartida) {
    this.discriminadorPartida = discriminadorPartida;
  }

  public String getDiscriminadorLlegada() {
    return discriminadorLlegada;
  }

  public void setDiscriminadorLlegada(String discriminadorLlegada) {
    this.discriminadorLlegada = discriminadorLlegada;
  }
}
