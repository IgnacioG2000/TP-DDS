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



  public Double calcularHuellaCarbonoPorSector(LocalDate fecha, boolean esMensual) {
    Double hcPorSector = RepoOrganizacion
        .getInstance()
        .listadoAreasOrganizaciones()
        .stream()
        .filter(this::perteneceASector)
        .collect(Collectors.toList())
        .stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalArea(fecha, esMensual))
        .sum();

    return hcPorSector;
  }

  private boolean perteneceASector(Area area) {
    return area.getEspacioDeTrabajo().getProvincia().equals(sectorTerritorial)
        || area.getEspacioDeTrabajo().getMunicipio().equals(sectorTerritorial);
  }
}
