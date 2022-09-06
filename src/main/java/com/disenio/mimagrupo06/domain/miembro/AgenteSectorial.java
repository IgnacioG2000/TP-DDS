package com.disenio.mimagrupo06.domain.miembro;

import com.disenio.mimagrupo06.apiDistancia.Sector;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Hogar;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;


import java.util.stream.Collectors;

public class AgenteSectorial extends Persona {

  private Sector sectorTerritorial;

  public AgenteSectorial(String nombre, String apellido, TipoDocumento tipoDocumento,
                         String nroDocumento, Hogar ubicacion, Usuario usuario, Sector sectorTerritorial) {
    super(nombre, apellido, tipoDocumento, nroDocumento, ubicacion, usuario);
    this.sectorTerritorial = sectorTerritorial;
  }

  public Sector getSectorTerritorial() {
    return sectorTerritorial;
  }

  //TODO:Consultar si es necesario Anual Y Mensual
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
}
