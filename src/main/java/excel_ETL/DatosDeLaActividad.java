package excel_ETL;

import java.time.LocalDate;

public class DatosDeLaActividad {
  private String actividad;
  private String tipoDeConsumo;
  private Consumo consumo;
  private String periodoDeImputacion;

//TODO
  //Faltan los constructores


  public DatosDeLaActividad() {
  }

  public String getActividad() {
    return actividad;
  }

  public String getTipoDeConsumo() {
    return tipoDeConsumo;
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

  public void setTipoDeConsumo(String tipoDeConsumo) {
    this.tipoDeConsumo = tipoDeConsumo;
  }

  public void setConsumo(Consumo consumo) {
    this.consumo = consumo;
  }

  public void setPeriodoDeImputacion(String periodoDeImputacion) {
    this.periodoDeImputacion = periodoDeImputacion;
  }
}
