package domain.huellaDeCarbono.trayecto;

import domain.huellaDeCarbono.espacio.Espacio;

import java.util.Collection;

public class Trayecto {
  private Espacio partida;
  private Espacio llegada;
  private Collection<Tramo> tramos;

  public Trayecto(Espacio partida, Espacio llegada, Collection<Tramo> tramos) {
    this.partida = partida;
    this.llegada = llegada;
    this.tramos = tramos;
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

/*
  public Double calcularDistanciaTotal(){
    return tramos.stream().mapToDouble(Tramo::calcularDistancia).sum();
  }
*/
}
