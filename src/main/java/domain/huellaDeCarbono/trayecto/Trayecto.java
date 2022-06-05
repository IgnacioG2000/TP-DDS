package domain.huellaDeCarbono.trayecto;

import domain.huellaDeCarbono.espacio.Espacio;
import domain.miembro.Miembro;

import java.util.Collection;

public class Trayecto {
  private Espacio partida;
  private Espacio llegada;
  private Collection<Tramo> tramos;
  private Collection<Miembro> miembros;

  public Trayecto(Espacio partida, Espacio llegada, Collection<Tramo> tramos, Collection<Miembro> miembros) {
    this.partida = partida;
    this.llegada = llegada;
    this.tramos = tramos;
    this.miembros = miembros;
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

  public Collection<Miembro> getMiembros() {
    return miembros;
  }

  public void setMiembros(Collection<Miembro> miembros) {
    this.miembros = miembros;
  }

  //TODO
  // public Float calcularDistanciaTotal(){
  // }

}
