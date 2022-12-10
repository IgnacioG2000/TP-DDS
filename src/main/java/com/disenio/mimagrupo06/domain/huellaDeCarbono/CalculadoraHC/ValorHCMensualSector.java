package com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC;

import com.disenio.mimagrupo06.domain.sector.Sector;
import com.disenio.mimagrupo06.seguridad.roles.AgenteSectorial;

import javax.persistence.*;

@Entity
public class ValorHCMensualSector {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  int anio;
  int mes;
  double huellaCarbono;
  @ManyToOne
  public Sector sector;
  @OneToOne // TODO agregue el agente acaa
  public AgenteSectorial agenteSectorial;

  public ValorHCMensualSector(int anio, int mes, double huellaCarbono, Sector sector, AgenteSectorial agenteSectorial) {
    this.anio = anio;
    this.mes = mes;
    this.huellaCarbono = huellaCarbono;
    this.sector = sector;
    this.agenteSectorial = agenteSectorial; // TODO Agregue esto
  }

  public ValorHCMensualSector() {

  }
  // TODO Ver funcion soy mes
  public boolean soyMes(int otroAnio, int otroMes, Sector sect,AgenteSectorial agente) {
    return otroAnio == anio && otroMes == mes && agenteSectorial == agente && sector == sect;
  }

  public double getHuellaCarbono() {
    return huellaCarbono;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getAnio() {
    return anio;
  }

  public void setAnio(int anio) {
    this.anio = anio;
  }

  public int getMes() {
    return mes;
  }

  public void setMes(int mes) {
    this.mes = mes;
  }

  public void setHuellaCarbono(double huellaCarbono) {
    this.huellaCarbono = huellaCarbono;
  }

  public Sector getSector() {
    return sector;
  }

  public void setSector(Sector sector) {
    this.sector = sector;
  }

  public AgenteSectorial getAgenteSectorial() {
    return agenteSectorial;
  }

  public void setAgenteSectorial(AgenteSectorial agenteSectorial) {
    this.agenteSectorial = agenteSectorial;
  }
}
