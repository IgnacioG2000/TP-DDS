package com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto;

import com.disenio.mimagrupo06.apiDistancia.ServicioApiDistancia;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;
import com.disenio.mimagrupo06.domain.miembro.Miembro;

import javax.persistence.*;
import java.io.IOException;
import java.util.Collection;

@Entity
public class Tramo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  private Espacio partida;
  @ManyToOne
  private Espacio llegada;
  @ManyToOne//(fetch = FetchType.LAZY)
  //@JoinColumn(name = "tramo_id")
  private MedioDeTransporte transporte;
  @ManyToMany
  private Collection<Miembro> miembros;
  private double distancia;

  public Tramo(Espacio partida, Espacio llegada, MedioDeTransporte transporte, Collection<Miembro> miembros) throws IOException {
    this.partida = partida;
    this.llegada = llegada;
    this.transporte = transporte;
    if(miembros.size() == 1 || transporte.puedoSerCompartido()) {
      this.miembros = miembros;
    }
    distancia =  ServicioApiDistancia.getInstancia().obtenerDistancia(partida, llegada);
  }

  public Tramo(){

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

  public double calcularHuellaCarbonoTramo() {
    return (this.transporte.getFactorEmision() * this.calcularDistancia()) / miembros.size();
  }

  public double calcularDistancia() {
    return distancia;
    //return ServicioApiDistancia.getInstancia().obtenerDistancia(llegada,partida);
  }

  public boolean tieneMiembro(Miembro miembro) {
    return miembros.contains(miembro);
  }

  public double getDistancia() {
    return distancia;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPartida(Espacio partida) {
    this.partida = partida;
  }

  public void setLlegada(Espacio llegada) {
    this.llegada = llegada;
  }

  public void setTransporte(MedioDeTransporte transporte) {
    this.transporte = transporte;
  }

  public void setMiembros(Collection<Miembro> miembros) {
    this.miembros = miembros;
  }

  public void setDistancia(double distancia) {
    this.distancia = distancia;
  }
}
