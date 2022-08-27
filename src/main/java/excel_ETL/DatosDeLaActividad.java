package excel_ETL;

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

  //TODO:arreglar
  public boolean perteneceMes(LocalDate fechaMensual) {
    String[] fechas = periodoDeImputacion.split("/");
    int fechaCompleta = Integer.parseInt(periodoDeImputacion);
    int fechaAnio = fechaCompleta % 10000;
    int fechaMes = fechaCompleta / 10000;
    return fechaAnio == fechaMensual.getYear() && fechaMes == fechaMensual.getMonthValue();
  }
}
