package repositorios;


import domain.organizacion.Area;
import domain.organizacion.Organizacion;

import java.util.Collection;

public class RepoOrganizacion {
  private Collection<Organizacion> organizaciones;

  private static final RepoOrganizacion INSTANCE = new RepoOrganizacion();

  public static RepoOrganizacion getInstance() {
    return INSTANCE;
  }

  public Organizacion encontrarOrganizacion(Area area){
    return organizaciones.stream().filter(organizacion->organizacion.tieneArea(area)).findFirst().get();
  }

}
