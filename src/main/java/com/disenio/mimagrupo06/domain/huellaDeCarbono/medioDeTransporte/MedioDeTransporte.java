package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_medio_transporte_st",discriminatorType = DiscriminatorType.INTEGER)
public abstract class MedioDeTransporte {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  protected double factorEmision;

  @OneToOne(mappedBy = "transporte")
  protected Tramo tramo;

  protected String nombreAMostrar;

  public String getNombreAMostrar() {
    return nombreAMostrar;
  }

  public void setNombreAMostrar(String nombreAMostrar) {
    this.nombreAMostrar = nombreAMostrar;
  }

  public Tramo getTramo() {
    return tramo;
  }

  public void setTramo(Tramo tramos) {
    this.tramo = tramos;
  }

  public MedioDeTransporte() {
  }

  public abstract boolean puedoSerCompartido();

  public double getFactorEmision() {
    return factorEmision;
  }

  public void setFactorEmision(double factorEmision) {
    this.factorEmision = factorEmision;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
