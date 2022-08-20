package domain.huellaDeCarbono.trayecto;

import apiDistancia.ApiDistancia;
import apiDistancia.ServicioApiDistancia;
import domain.huellaDeCarbono.espacio.Espacio;
import domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;
import domain.miembro.Miembro;

import java.io.IOException;
import java.util.Collection;

public class Tramo {
  private Espacio partida;
  private Espacio llegada;
  private MedioDeTransporte transporte;
  private Collection<Miembro> miembros;
  private Double periodicidad;
  private Double peso;

  public Tramo(Espacio partida, Espacio llegada, MedioDeTransporte transporte, Collection<Miembro> miembros, Double peso) {
    this.partida = partida;
    this.llegada = llegada;
    this.transporte = transporte;
    if(miembros.size() == 1 || transporte.puedoSerCompartido()) {
      this.miembros = miembros;
    }
    this.peso = peso;
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

  public void agregarMiembro(Miembro miembro, Double nuevaPeriodicidad) {
    if(transporte.puedoSerCompartido()) {
      miembros.add(miembro);
      periodicidad += nuevaPeriodicidad;
    }
  }

  public Double calcularHuellaCarbonoTramo() throws IOException {
    return this.transporte.getFactorEmision() * this.calcularDistancia() * this.getPeriodicidad();
  }


  public Double calcularDistancia() throws IOException {
    return ServicioApiDistancia.getInstancia().obtenerDistancia(llegada,partida);
  }

  public boolean tieneMiembro(Miembro miembro) {
    return miembros.contains(miembro);
  }

  //TODO: Si a la periodicidad le cambiamos por un valor que seteamos, ya estaria (
  public Double getPeriodicidad(){
    return periodicidad/ miembros.size();
  }

  public Double getPeso() {
    return peso;
  }


}
