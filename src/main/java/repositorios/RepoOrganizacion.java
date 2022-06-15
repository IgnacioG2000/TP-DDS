package repositorios;


import domain.organización.Area;
import domain.organización.Organizacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class RepoOrganizacion {
  private Collection<Organizacion> organizaciones;

  private static final RepoOrganizacion INSTANCE = new RepoOrganizacion();

  public static RepoOrganizacion getInstance() {
    return INSTANCE;
  }
/*
  public Organizacion encontrarOrganizacion(Area area){
    return organizaciones.stream().filter(organizacion->organizacion.tieneArea(area)).findFirst().get();
  }
*/
}
