package repositorios;


import domain.organizacion.Area;
import domain.organizacion.Contacto;
import domain.organizacion.Organizacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepoOrganizacion {
  private Collection<Organizacion> organizaciones;

  private static final RepoOrganizacion INSTANCE = new RepoOrganizacion();

  public static RepoOrganizacion getInstance() {
    return INSTANCE;
  }

  public Collection<Organizacion> getOrganizaciones() {
    return organizaciones;
  }

  public Organizacion encontrarOrganizacion(Area area){
    return organizaciones.stream().filter(organizacion->organizacion.tieneArea(area)).findFirst().get();
  }

  public List<Contacto> listadoContactosOrganizaciones (){
    List<Contacto> lista = new ArrayList<>();
    organizaciones.stream().forEach(o -> lista.addAll(o.getContactos()));
    return lista;
  }

}
