package domain.huellaDeCarbono.trayecto;

import domain.huellaDeCarbono.espacio.Espacio;
import domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;
import domain.miembro.Miembro;
import java.util.Collection;

public class Tramo {
  private Espacio partida;
  private Espacio llegada;
  private MedioDeTransporte transporte;
  private Collection<Miembro> miembros;

  public Tramo(Espacio partida, Espacio llegada, MedioDeTransporte transporte, Collection<Miembro> miembros) {
    this.partida = partida;
    this.llegada = llegada;
    this.transporte = transporte;
    if(miembros.size() == 1 || transporte.puedoSerCompartido()) {
      this.miembros = miembros;
    }
  }

  public Espacio getPartida() {
    return partida;
  }

  public Espacio getLlegada() {
    return llegada;
  }

  public MedioDeTransporte getTransporte() {
    return transporte;
  }

  public Collection<Miembro> getMiembros(){ return miembros;}

  public void agregarMiembro(Miembro miembro) {
    if(transporte.puedoSerCompartido()) {
      miembros.add(miembro);
    }
  }

/*
  public Double calcularDistancia(){
    return ApiDistancia.getInstance().obtenerDistancia(llegada,partida);
  }
 */
}
