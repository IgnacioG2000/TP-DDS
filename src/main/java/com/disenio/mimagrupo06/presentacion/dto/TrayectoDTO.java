package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;

import java.time.LocalDate;
import java.util.Collection;

public class TrayectoDTO {

  private String nombreArea;
  private double latitudPartida;
  private double longitudPartida;
  private String provinciaPartida;
  private String municipioPartida;
  private String localidadPartida;
  private String direccionPartida;
  private String numeroPartida;
  private float codigoPostalPartida;
  private double latitudLlegada;
  private double longitudLlegada;
  private String provinciaLlegada;
  private String municipioLlegada;
  private String localidadLlegada;
  private String direccionLlegada;
  private String numeroLlegada;
  private float codigoPostalLlegada;
  private Collection<Tramo> tramos;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private int diasUtilizados;



  public double getLatitudPartida() {
    return latitudPartida;
  }

  public void setLatitudPartida(double latitudPartida) {
    this.latitudPartida = latitudPartida;
  }

  public double getLongitudPartida() {
    return longitudPartida;
  }

  public void setLongitudPartida(double longitudPartida) {
    this.longitudPartida = longitudPartida;
  }

  public String getProvinciaPartida() {
    return provinciaPartida;
  }

  public void setProvinciaPartida(String provinciaPartida) {
    this.provinciaPartida = provinciaPartida;
  }

  public String getMunicipioPartida() {
    return municipioPartida;
  }

  public void setMunicipioPartida(String municipioPartida) {
    this.municipioPartida = municipioPartida;
  }

  public String getLocalidadPartida() {
    return localidadPartida;
  }

  public void setLocalidadPartida(String localidadPartida) {
    this.localidadPartida = localidadPartida;
  }

  public String getDireccionPartida() {
    return direccionPartida;
  }

  public void setDireccionPartida(String direccionPartida) {
    this.direccionPartida = direccionPartida;
  }

  public String getNumeroPartida() {
    return numeroPartida;
  }

  public String getNombreArea() {
    return nombreArea;
  }

  public void setNombreArea(String nombreArea) {
    this.nombreArea = nombreArea;
  }

  public void setNumeroPartida(String numeroPartida) {
    this.numeroPartida = numeroPartida;
  }

  public float getCodigoPostalPartida() {
    return codigoPostalPartida;
  }

  public void setCodigoPostalPartida(float codigoPostalPartida) {
    this.codigoPostalPartida = codigoPostalPartida;
  }

  public double getLatitudLlegada() {
    return latitudLlegada;
  }

  public void setLatitudLlegada(double latitudLlegada) {
    this.latitudLlegada = latitudLlegada;
  }

  public double getLongitudLlegada() {
    return longitudLlegada;
  }

  public void setLongitudLlegada(double longitudLlegada) {
    this.longitudLlegada = longitudLlegada;
  }

  public String getProvinciaLlegada() {
    return provinciaLlegada;
  }

  public void setProvinciaLlegada(String provinciaLlegada) {
    this.provinciaLlegada = provinciaLlegada;
  }

  public String getMunicipioLlegada() {
    return municipioLlegada;
  }

  public void setMunicipioLlegada(String municipioLlegada) {
    this.municipioLlegada = municipioLlegada;
  }

  public String getLocalidadLlegada() {
    return localidadLlegada;
  }

  public void setLocalidadLlegada(String localidadLlegada) {
    this.localidadLlegada = localidadLlegada;
  }

  public String getDireccionLlegada() {
    return direccionLlegada;
  }

  public void setDireccionLlegada(String direccionLlegada) {
    this.direccionLlegada = direccionLlegada;
  }

  public String getNumeroLlegada() {
    return numeroLlegada;
  }

  public void setNumeroLlegada(String numeroLlegada) {
    this.numeroLlegada = numeroLlegada;
  }

  public float getCodigoPostalLlegada() {
    return codigoPostalLlegada;
  }

  public void setCodigoPostalLlegada(float codigoPostalLlegada) {
    this.codigoPostalLlegada = codigoPostalLlegada;
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

  public void setDiasUtilizados(int diasUtilizados) {
    this.diasUtilizados = diasUtilizados;
  }
}
