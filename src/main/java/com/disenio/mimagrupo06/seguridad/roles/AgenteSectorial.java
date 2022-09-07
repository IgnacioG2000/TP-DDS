package com.disenio.mimagrupo06.seguridad.roles;

import com.disenio.mimagrupo06.domain.sector.Sector;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("3")
public class AgenteSectorial extends Usuario {
  @ManyToOne
  private Sector sectorTerritorial;

  public AgenteSectorial(String usuario, String contrasenia,Sector sector) throws NoSuchAlgorithmException {
    super(usuario, contrasenia);
    this.sectorTerritorial = sector;
  }

  public AgenteSectorial() {

  }

  public Sector getSectorTerritorial() {
    return sectorTerritorial;
  }

  public double calcularHuellaCarbonoPorSectorAnual(int anio) {
    double hcPorSector = RepoOrganizacion
        .getInstance()
        .listadoAreasOrganizaciones()
        .stream()
        .filter(area -> area.perteneceSector(sectorTerritorial))
        .collect(Collectors.toList())
        .stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaAnual(anio))
        .sum();

    return hcPorSector;
  }

  //TODO:Consultar si es necesario Anual Y Mensual
  public double calcularHuellaCarbonoPorSectorMensual(int anio, int mes) {
    double hcPorSector = RepoOrganizacion
        .getInstance()
        .listadoAreasOrganizaciones()
        .stream()
        .filter(area -> area.perteneceSector(sectorTerritorial))
        .collect(Collectors.toList())
        .stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaMensual(anio, mes))
        .sum();

    return hcPorSector;
  }

  public void setSectorTerritorial(Sector sectorTerritorial) {
    this.sectorTerritorial = sectorTerritorial;
  }
}
