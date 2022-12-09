package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo",discriminatorType = DiscriminatorType.INTEGER)
public abstract class MedioDeTransporte {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  protected double factorEmision;

  @OneToOne(mappedBy = "transporte")
  protected Tramo tramo;

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
