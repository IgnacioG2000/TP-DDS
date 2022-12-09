package com.disenio.mimagrupo06.domain.sector;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.ValorHCMensualSector;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_st",discriminatorType = DiscriminatorType.INTEGER)
public abstract class Sector {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;
  @OneToMany
  @JoinColumn(name = "sector_id")
  private List<ValorHCMensualSector> valoresHCMensualSector;


  public abstract String getNombre();
  public abstract String nombreProvincia();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
