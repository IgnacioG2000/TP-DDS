package domain.huellaDeCarbono.trayecto;

import domain.huellaDeCarbono.espacio.Espacio;
import domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;

public class Tramo {
  private Espacio partida;
  private Espacio llegada;
  private MedioDeTransporte transporte;

  public Tramo(Espacio partida, Espacio llegada, MedioDeTransporte transporte) {
    this.partida = partida;
    this.llegada = llegada;
    this.transporte = transporte;
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

  public MedioDeTransporte getTransporte() {
    return transporte;
  }

  public void setTransporte(MedioDeTransporte transporte) {
    this.transporte = transporte;
  }
}
