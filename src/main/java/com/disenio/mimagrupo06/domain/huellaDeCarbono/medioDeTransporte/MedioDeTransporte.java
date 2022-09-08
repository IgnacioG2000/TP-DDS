package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo",discriminatorType = DiscriminatorType.INTEGER)
public abstract class MedioDeTransporte {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  protected double factorEmision;

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
