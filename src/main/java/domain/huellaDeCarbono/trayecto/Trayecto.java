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
  //TODO: Ahora la fecha de inicio y fin, va a estar en tramo y lo sacamos de aca????
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

  public Double calcularHCTrayectoMensual() throws IOException {
    double coeficiente = Double.parseDouble(ArchivoConfig.obtenerCoeficienteHCMensual());
    return this.calcularHCTrayectoSemanal() * coeficiente;
  }

  public boolean perteneceMiembro(Miembro miembro) {
    return tramos.stream().allMatch(tramo -> tramo.tieneMiembro(miembro));
  }

  public boolean perteneceMes(LocalDate fecha) {
    return this.perteneceAnio(fecha) && fechaInicio.getMonth() == fecha.getMonth();
  }

  public boolean perteneceAnio(LocalDate fecha){
    return fechaInicio.getYear() == fecha.getYear();
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
