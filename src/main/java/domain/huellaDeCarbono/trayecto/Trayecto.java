package domain.huellaDeCarbono.trayecto;

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

  public Trayecto(Espacio partida, Espacio llegada, Collection<Tramo> tramos, LocalDate fechaInicio) {
    this.partida = partida;
    this.llegada = llegada;
    this.tramos = tramos;
    this.fechaInicio = fechaInicio;
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


  public Double calcularHuellaCarbonoTotalTrayecto() {
    Double hcTramo = tramos.stream().mapToDouble(unTramo -> {
      try {
        return unTramo.calcularHuellaCarbonoTramo();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return 0;
    }).sum();
    return hcTramo;
  }

  public boolean perteneceMiembro(Miembro miembro) {
    return tramos.stream().allMatch(tramo -> tramo.tieneMiembro(miembro));
  }

  public boolean perteneceMes(LocalDate fecha){
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
}
