package domain.miembro;

import apiDistancia.Sector;
import domain.huellaDeCarbono.espacio.Hogar;
import repositorios.RepoOrganizacion;
import seguridad.roles.Usuario;

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
  public Double calcularHuellaCarbonoPorSectorAnual(int anio) {
    Double hcPorSector = RepoOrganizacion
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
  public Double calcularHuellaCarbonoPorSectorMensual(int anio, int mes) {
    Double hcPorSector = RepoOrganizacion
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
