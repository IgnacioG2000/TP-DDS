package excel_ETL;

import java.sql.SQLSyntaxErrorException;
import java.time.LocalDate;
import java.util.List;

public class DatosDeLaActividad {
  private String actividad;
  private String tipoDeConsumo;
  private Consumo consumo;
  private String periodoDeImputacion;

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
}
