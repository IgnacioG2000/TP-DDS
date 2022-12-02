package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;

public class TramoDTO {
  private Long id;
  private EspacioDTO partida;
  private EspacioDTO llegada;
  private MedioDeTransporteDTO transporte;

  public TramoDTO( EspacioDTO partida, EspacioDTO llegada, MedioDeTransporteDTO transporte) {
    this.partida = partida;
    this.llegada = llegada;
    this.transporte = transporte;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EspacioDTO getPartida() {
    return partida;
  }

  public void setPartida(EspacioDTO partida) {
    this.partida = partida;
  }

  public EspacioDTO getLlegada() {
    return llegada;
  }

  public void setLlegada(EspacioDTO llegada) {
    this.llegada = llegada;
  }

  public MedioDeTransporteDTO getTransporte() {
    return transporte;
  }

  public void setTransporte(MedioDeTransporteDTO transporte) {
    this.transporte = transporte;
  }
}
