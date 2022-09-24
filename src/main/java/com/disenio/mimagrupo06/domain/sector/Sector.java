package com.disenio.mimagrupo06.domain.sector;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo",discriminatorType = DiscriminatorType.INTEGER)
public abstract class Sector {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;


  public abstract String getNombre();
  public abstract String nombreProvincia();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
