package com.disenio.mimagrupo06.seguridad.roles;

import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.sector.Sector;
import com.disenio.mimagrupo06.repositorios.RepoArea;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion1;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("3")
public class AgenteSectorial extends Usuario {
  @ManyToOne
  private Sector sectorTerritorial;
  @Transient
  private RepoArea ra;

  public RepoArea getRa() {
    return ra;
  }

  public void setRa(RepoArea ra) {
    this.ra = ra;
  }

  public AgenteSectorial(String usuario, String contrasenia, Sector sector) throws NoSuchAlgorithmException {
    super(usuario, contrasenia);
    this.sectorTerritorial = sector;
    setTipoUsuario(3);
  }

  public AgenteSectorial() {

  }

  public Sector getSectorTerritorial() {
    return sectorTerritorial;
  }

  public double calcularHuellaCarbonoPorSectorAnual(int anio) {
    List<Area> listaAreas = ra.findAll();
    double hcPorSector = listaAreas
        .stream()
        .filter(area -> area.perteneceSector(sectorTerritorial))
        .collect(Collectors.toList())
        .stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaAnual(anio))
        .sum();

    return hcPorSector;
  }

  public double calcularHuellaCarbonoPorSectorMensual(int anio, int mes) {
    List<Area> listaAreas = ra.findAll();
    double hcPorSector = listaAreas
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
