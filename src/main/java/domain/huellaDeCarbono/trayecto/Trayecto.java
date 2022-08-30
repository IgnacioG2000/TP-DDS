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

  //TODO : Como se que trayecto tomar si tiene fecha de inicio (Un mes particular) pero yo estoy en otro mes
  //TODO : La fecha de fin se agrega o no? (Relacionado a lo anterior)


  //TODO: El HC va a ser calculado aca, el area cuando calcule por sus trayectos, aca se va a calcular el HC del trayecto semanal con el peso por persona asociado
  // Calcula por semana
  public Double calcularHuellaCarbonoTotalTrayecto() {
    Double hcTrayecto = tramos.stream().mapToDouble(unTramo -> {
      try {
        return unTramo.calcularHuellaCarbonoTramo();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return 0;
    }).sum();
    return hcTrayecto;
  }

  public Double calcularHCTrayectoSemanal() {
    return this.calcularHuellaCarbonoTotalTrayecto() * this.peso();
  }

  public Double calcularHCTrayectoMensual() {
    double coeficiente = 0.0;
    try{
      coeficiente = Double.parseDouble(ArchivoConfig.obtenerCoeficienteHCMensual());
    }catch(IOException e){
      e.printStackTrace();
    }

    return this.calcularHCTrayectoSemanal() * coeficiente;
  }

  public boolean perteneceMiembro(Miembro miembro) {
    return tramos.stream().allMatch(tramo -> tramo.tieneMiembro(miembro));
  }

  public boolean perteneceMes(int anio, int mes) {
    return this.perteneceAnio(anio) && fechaInicio.getMonthValue() == mes;
  }

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
