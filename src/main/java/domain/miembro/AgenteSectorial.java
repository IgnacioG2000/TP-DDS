package domain.miembro;

import domain.huellaDeCarbono.espacio.Hogar;
import domain.organizacion.Area;
import repositorios.RepoOrganizacion;
import seguridad.roles.Usuario;

import java.time.LocalDate;
import java.util.Locale;
import java.util.stream.Collectors;

public class AgenteSectorial extends Persona {
  private String sectorTerritorial;

  public AgenteSectorial(String nombre, String apellido, TipoDocumento tipoDocumento,
                         String nroDocumento, Hogar ubicacion, Usuario usuario, String sectorTerritorial) {
    super(nombre, apellido, tipoDocumento, nroDocumento, ubicacion, usuario);
    this.sectorTerritorial = sectorTerritorial;
  }

  public String getSectorTerritorial() {
    return sectorTerritorial;
  }

  public Double calcularHuellaCarbonoPorSector(LocalDate fecha, boolean esMensual) {
    Double hcPorSector = RepoOrganizacion
        .getInstance()
        .listadoAreasOrganizaciones()
        .stream()
        .filter(area -> area.perteneceAArea(this))
        .collect(Collectors.toList())
        .stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalArea(fecha, esMensual))
        .sum();

    return hcPorSector;
  }
}
