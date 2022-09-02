package domain.huellaDeCarbono.trayecto;

import apiDistancia.ArchivoConfig;
import domain.huellaDeCarbono.espacio.Espacio;
import domain.miembro.Miembro;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

public class Trayecto {
  private Espacio partida;
  private Espacio llegada;
  private Collection<Tramo> tramos;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private int diasUtilizados;

  public Trayecto(Espacio partida, Espacio llegada, Collection<Tramo> tramos, LocalDate fechaInicio, int diasUtilizados) {
    this.partida = partida;
    this.llegada = llegada;
    this.tramos = tramos;
    this.fechaInicio = fechaInicio;
    this.diasUtilizados = diasUtilizados;
  }

  public Espacio getPartida() {
    return partida;
  }

  public void setPartida(Espacio partida) {
    this.partida = partida;
  }

  public Espacio getLlegada() {
    return llegada;
  }

  public void setLlegada(Espacio llegada) {
    this.llegada = llegada;
  }

  public Collection<Tramo> getTramos() {
    return tramos;
  }

  public void setTramos(Collection<Tramo> tramos) {
    this.tramos = tramos;
  }

  public double calcularHuellaCarbonoTotalTrayecto() {
    double hcTrayecto = tramos.stream().mapToDouble(Tramo::calcularHuellaCarbonoTramo).sum();
    return hcTrayecto;
  }

  public double calcularHCTrayectoSemanal() {
    return this.calcularHuellaCarbonoTotalTrayecto() * this.peso() * 5;
  }

  public double calcularHCTrayectoMensual() {
    double coeficiente = 0.0;
    try{
      coeficiente = Double.parseDouble(ArchivoConfig.obtenerCoeficienteHCMensual());

    }catch(IOException e){
      e.printStackTrace();
    }

    double resultado = this.calcularHCTrayectoSemanal() * coeficiente;
    return resultado;
  }

  public boolean perteneceMiembro(Miembro miembro) {
    return tramos.stream().allMatch(tramo -> tramo.tieneMiembro(miembro));
  }

  public boolean perteneceMes(int anio, int mes) {
    boolean rta;
    if(fechaFin==null){
      rta = perteneceMesTrayNoFin(anio,mes);
    }else{
      rta = perteneceMesTrayFin(anio, mes);
    }
    return rta;
  }

  public boolean perteneceMesTrayFin(int anio, int mes){
    return (fechaInicio.getYear() < anio) && (fechaFin.getYear() > anio)
        || (fechaInicio.getYear() < anio) && (fechaFin.getYear() == anio) && (fechaFin.getMonthValue() >= mes)
        || (fechaInicio.getYear() == anio) && (fechaInicio.getMonthValue() <= mes) && (fechaFin.getYear() > anio)
        || (fechaInicio.getYear() == anio) && (fechaInicio.getMonthValue() <= mes) && (fechaFin.getYear() == anio) && (fechaFin.getMonthValue() >= mes);
  }
  /*
  fecha inicio 2020 mes inicio 11
  fecha fin    2021 mes final  3
  anio 2021 mes 5               FALSE

  fecha inicio 2020 mes inicio 11
  fecha fin    2022 mes final  3
  anio 2021 mes 5               TRUE

  fecha inicio 2020 mes inicio 11
  fecha fin    2021 mes final  6 o 5
  anio 2021 mes 5               TRUE

  fecha inicio 2020 mes inicio 11
  fecha fin    2021 mes final  6 o 5
  anio 2020 mes 5               FALSE

  fecha inicio 2020 mes inicio 11
  fecha fin    2021 mes final  6 o 5
  anio 2020 mes 11               TRUE

  fecha inicio 2020 mes inicio 11
  fecha fin    2021 mes final  6 o 5
  anio 2020 mes 12               TRUE

  fecha inicio 2020 mes inicio 2
  fecha fin    2020 mes final  4
  anio 2020 mes 1               FALSE

  fecha inicio 2020 mes inicio 2
  fecha fin    2020 mes final  4
  anio 2020 mes 5               FALSE

  fecha inicio 2020 mes inicio 2
  fecha fin    2020 mes final  4
  anio 2020 mes 2 o 3 o 4       TRUE
   */

  public boolean perteneceMesTrayNoFin(int anio, int mes){
    return (fechaInicio.getYear() < anio)
        || (fechaInicio.getYear() == anio) && (fechaInicio.getMonthValue() < mes)
        || (fechaInicio.getYear() == anio) && (fechaInicio.getMonthValue() == mes) && (fechaInicio.getMonthValue() < LocalDate.now().getMonthValue());
  }
  /*
  fecha inicio 2020 mes inicio 11
  anio 2021 mes 2               TRUE

  fecha inicio 2021 mes inicio 2
  fecha final  2022 mes final 1

  fecha inicio 2022 mes inicio 2
  anio 2022 mes 3               TRUE

  fecha inicio 2022 mes inicio 2
  anio 2022 mes 2               TRUE (DEPENDIENDO DEL MES ACTUAL)
   */


  public boolean perteneceAnio(int anio){
    return fechaInicio.getYear() == anio;
  }

/*
  public Double calcularDistanciaTotal(){
    return tramos.stream().mapToDouble(Tramo::calcularDistancia).sum();
  }
*/

  public double peso() {
    return (double) this.diasUtilizados / 5;
  }

  public void setFechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
  }
}
